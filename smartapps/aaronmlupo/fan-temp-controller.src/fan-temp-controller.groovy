definition(
    name: "Fan Temp Controller",
    namespace: "aaronmlupo",
    author: "Aaron Lupo",
    description: "Controller script for activating ceiling fan based on temperature",
    category: "My Apps",
    iconUrl: "http://cdn.device-icons.smartthings.com/Lighting/light24-icn@2x.png",
    iconX2Url: "http://cdn.device-icons.smartthings.com/Lighting/light24-icn@2x.png")

preferences {
    section("Temperature Sensors to Monitor:") {
        input "temperatureDetectors", "capability.temperatureMeasurement", required: true, title: "Please Select:"
    }
    section("Motion Detectors to Monitor:") {
        input "motionDetectors", "capability.motionSensor", required: true, title: "Please Select:"
    }
    section("Fan to Control:") {
        input "fanList", "capability.switch", required: true, title: "Please Select:"
    }
    section("Automation Switch:") {
    	input "automationSwitch", "capability.switch", required: true, title: "Please Select:"
    }
    section("Activation Temperature:") {
    	input "activationTemp", "number", required: true, title: "Degrees F:"
    }
    section("Maximum Temperature:") {
    	input "maxTemp", "number", required: true, title: "Degrees F:"
    }
    section("Timeout (seconds):") {
        input "timeoutSeconds", "number", required: true, title: "Timeout in Seconds:"
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
	subscribe(temperatureDetectors, "temperature", temperatureHandler)
    subscribe(motionDetectors, "motion.active", motionDetectedHandler)
    subscribe(motionDetectors, "motion.inactive", motionStoppedHandler)
}

def temperatureHandler(evt) {
	if (automationSwitch.currentValue("switch") == "on") {
		if (temperatureDetectors.currentTemperature <= activationTemp) {
	    	fanList.off()
	    } else {
	        if (temperatureDetectors.currentTemperature >= maxTemp) {
	        	// fanList.on()
	            if (fanList.currentValue("switch") == "on") {
		            fanList.setLevel(100)
	            }
	        } else {
	        	def fanSpeed = (100 / (maxTemp - activationTemp)) * (temperatureDetectors.currentTemperature - activationTemp)
	            // fanList.on()
	            if (fanList.currentValue("switch") == "on") {
	            	fanList.setLevel(fanSpeed)
	            }
	        }
	    }
	}
}

def motionDetectedHandler(evt) {
	if (automationSwitch.currentValue("switch") == "on" ) {
	    log.debug "motionDetectedHandler called: $evt"
		log.debug "location.mode = $location.mode, sceneMode = $sceneMode, location.modes = $location.modes"
	    // fanList.each {aSwitch ->
	    //     aSwitch.on()
		//}
		if (temperatureDetectors.currentTemperature > activationTemp) {
	        if (temperatureDetectors.currentTemperature >= maxTemp) {
	        	fanList.on()
		        fanList.setLevel(100)
	        } else {
	        	def fanSpeed = (100 / (maxTemp - activationTemp)) * (temperatureDetectors.currentTemperature - activationTemp)
	            fanList.on()
	            fanList.setLevel(fanSpeed)
	        }
	    }
	}
}

def motionStoppedHandler(evt) {
	if (automationSwitch.currentValue("switch") == "on") {
	    log.debug "motionStoppedHandler called: $evt"
	    log.debug "location.mode = $location.mode, sceneMode = $sceneMode, location.modes = $location.modes"
	    runIn(timeoutSeconds, checkMotion)
    }
}

def checkMotion() {
	if (automationSwitch.currentValue("switch") == "on") {
	    log.debug "In checkMotion scheduled method"

	    def motionState = motionDetectors.currentState("motion")

	    if (motionState.value == "inactive") {
	        // get the time elapsed between now and when the motion reported inactive
	        def elapsed = now() - motionState.date.time

	        // elapsed time is in milliseconds, so the threshold must be converted to milliseconds too
	        def threshold = 1000 * (timeoutSeconds - 1)

	        if (elapsed >= threshold) {
	            log.debug "Motion has stayed inactive long enough since last check ($elapsed ms):  turning switch off"
	            fanList.each {aSwitch ->
	                aSwitch.off()
	    		}
	        } else {
	            log.debug "Motion has not stayed inactive long enough since last check ($elapsed ms):  doing nothing"
	        }
	    } else {
	        // Motion active; just log it and do nothing
	        log.debug "Motion is active, do nothing and wait for inactive"
	    }
	}
}
