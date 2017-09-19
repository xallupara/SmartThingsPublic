definition(
    name: "Disable Fan Automation",
    namespace: "aaronmlupo",
    author: "Aaron Lupo",
    description: "This SmartApp sets a flag that disables ceiling fan automation.",
    category: "My Apps",
    iconUrl: "http://cdn.device-icons.smartthings.com/Home/home1-icn@2x.png",
    iconX2Url: "http://cdn.device-icons.smartthings.com/Home/home1-icn@2x.png")

preferences {
	page(name: "selectRoutine")
}

def selectRoutine() {
    dynamicPage(name: "selectRoutine", install: true, uninstall: true) {
        // get the available actions
        def actions = location.helloHome?.getPhrases()*.label
        if (actions) {
            // sort them alphabetically
            actions.sort()
            section("Routines") {
                log.trace actions
                // use the actions as the options for an enum input
                input "action", "enum", title: "Select routine to monitor", options: actions
            }
            section("Select a Fan:") {
                input "switchList", "capability.switch", required: true
            }
        }
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
    subscribe(location, "routineExecuted", setFanDisabled)
}

def setFanDisabled(evt) {
    log.debug "routineChanged: $evt"

    // name will be "routineExecuted"
    log.debug "evt name: ${evt.name}"

    // value will be the ID of the SmartApp that created this event
    log.debug "evt value: ${evt.value}"

    // displayName will be the name of the routine
    // e.g., "I'm Back!" or "Goodbye!"
    log.debug "evt displayName: ${evt.displayName}"

    // descriptionText will be the name of the routine, followed by the action
    // e.g., "I'm Back! was executed" or "Goodbye! was executed"
    log.debug "evt descriptionText: ${evt.descriptionText}"
    
    log.debug "action: ${settings.action}"
    // def routineName = location.helloHome?.getPhrases(action).label
    
	if (evt.displayName == action) {
    	log.debug "Yep, this one"
    }
}