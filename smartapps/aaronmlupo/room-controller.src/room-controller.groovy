definition(
    name: "Room Controller",
    namespace: "aaronmlupo",
    author: "Aaron Lupo",
    description: "Controller for room lighting using motion detectors",
    category: "My Apps",
    iconUrl: "http://cdn.device-icons.smartthings.com/Lighting/light11-icn@2x.png",
    iconX2Url: "http://cdn.device-icons.smartthings.com/Lighting/light11-icn@2x.png")

preferences {
    // section("Select Mode:") {
    //	input "sceneMode", "mode", title: "Select a mode:"
	// }
    section("Motion Detectors to Monitor:") {
        input "motionDetectors", "capability.motionSensor", required: true, title: "Please Select:"
    }
    section("Lights/Switches to Control") {
        input "switchList", "capability.switch", required: true
    }
    section("Timeout (seconds):") {
        input "timeoutSeconds", "number", required: true, title: "Timeout in Seconds:"
    }
    section("Low Light Value (Percent):") {
        input "lowLightValue", "number", required: true, title: "Low Light Value:"
    }
    section("High Light Value (Percent):") {
    	input "highLightValue", "number", required: true, title: "High Light Value:"
    }
}

def installed() {
    initialize()
}

def updated() {
    unsubscribe()
    initialize()
}

def initialize() {
    subscribe(motionDetectors, "motion.active", motionDetectedHandler)
    subscribe(motionDetectors, "motion.inactive", motionStoppedHandler)
}

def motionDetectedHandler(evt) {
    log.debug "motionDetectedHandler called: $evt"
	log.debug "location.mode = $location.mode, sceneMode = $sceneMode, location.modes = $location.modes"
    // if (location.mode == sceneMode) {
	    switchList.each {aSwitch ->
	    	def mySwitchCaps = aSwitch.capabilities
	        def capLevel = false
	    	mySwitchCaps.each {cap ->
	    		log.debug "Capability name: ${cap.name}"
	            if (cap.name == "Switch Level") {
	            	capLevel = true
	            }
			}
	        if (capLevel) {
	        	log.debug "switch supports level"
	            aSwitch.setLevel(highLightValue,1)
	            aSwitch.on()
	        } else {
	        	aSwitch.on()
	        }
	    }
	// }
}

def motionStoppedHandler(evt) {
    log.debug "motionStoppedHandler called: $evt"
    log.debug "location.mode = $location.mode, sceneMode = $sceneMode, location.modes = $location.modes"
    // if (location.mode == sceneMode) {
	    runIn(timeoutSeconds, checkMotion)
    // }
}

def checkMotion() {
    log.debug "In checkMotion scheduled method"

    def motionState = motionDetectors.currentState("motion")

    if (motionState.value == "inactive") {
        // get the time elapsed between now and when the motion reported inactive
        def elapsed = now() - motionState.date.time

        // elapsed time is in milliseconds, so the threshold must be converted to milliseconds too
        def threshold = 1000 * (timeoutSeconds - 1)

        if (elapsed >= threshold) {
            log.debug "Motion has stayed inactive long enough since last check ($elapsed ms):  turning switch off"
            switchList.each {aSwitch ->
    			def mySwitchCaps = aSwitch.capabilities
        		def capLevel = false
    			mySwitchCaps.each {cap ->
    				log.debug "Capability name: ${cap.name}"
            		if (cap.name == "Switch Level") {
            			capLevel = true
            		}
				}
        		if ((capLevel) && (lowLightValue > 0)) {
        			log.debug "switch supports level"
            		aSwitch.setLevel(lowLightValue,5)
        		} else {
                	aSwitch.off()
                }
    		}
        } else {
            log.debug "Motion has not stayed inactive long enough since last check ($elapsed ms):  doing nothing"
        }
    } else {
        // Motion active; just log it and do nothing
        log.debug "Motion is active, do nothing and wait for inactive"
    }
}