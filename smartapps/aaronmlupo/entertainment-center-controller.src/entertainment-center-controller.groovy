definition(
    name: "Entertainment Center Controller",
    namespace: "aaronmlupo",
    author: "Aaron Lupo",
    description: "Controller script for running the entertainment center",
    category: "My Apps",
    iconUrl: "http://cdn.device-icons.smartthings.com/Lighting/light24-icn@2x.png",
    iconX2Url: "http://cdn.device-icons.smartthings.com/Lighting/light24-icn@2x.png")

preferences {
    section("Entertainment System Switch") {
        input "entCenterSwitch", "capability.switch", required: true
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
    subscribe(entCenterSwitch, "switch", entCenterHandler)
}

def entCenterHandler(evt) {
	if (entCenterSwitch.currentValue("switch") == "on") {
    	rqTVon()
        rqRECEIVERon()
        rqVolumeReset()
	} else {
    	rqTVoff()
        rqVolumeZero()
        rqRECEIVERoff()
    }
}

def rqTVon() {
    def params = [
        uri:  'http://192.168.2.249:5000',
        path: '/tv/power/on'
    ]
    try {
        httpGet(params) {resp ->
            log.debug "resp data: ${resp.data}"
            log.debug "humidity: ${resp.data.main.humidity}"
        }
    } catch (e) {
        log.error "error: $e"
    }
}

def rqTVoff() {
    def params = [
        uri:  'http://192.168.2.249:5000',
        path: '/tv/power/off'
    ]
    try {
        httpGet(params) {resp ->
            log.debug "resp data: ${resp.data}"
            log.debug "humidity: ${resp.data.main.humidity}"
        }
    } catch (e) {
        log.error "error: $e"
    }
}

def rqRECEIVERon() {
    def params = [
        uri:  'http://192.168.2.249:5000',
        path: '/tv/receiver/on'
    ]
    try {
        httpGet(params) {resp ->
            log.debug "resp data: ${resp.data}"
            log.debug "humidity: ${resp.data.main.humidity}"
        }
    } catch (e) {
        log.error "error: $e"
    }
}

def rqRECEIVERoff() {
    def params = [
        uri:  'http://192.168.2.249:5000',
        path: '/tv/receiver/off'
    ]
    try {
        httpGet(params) {resp ->
            log.debug "resp data: ${resp.data}"
            log.debug "humidity: ${resp.data.main.humidity}"
        }
    } catch (e) {
        log.error "error: $e"
    }
}

def rqVolumeZero() {
    def params = [
        uri:  'http://192.168.2.249:5000',
        path: '/receiver/volume/zero'
    ]
    try {
        httpGet(params) {resp ->
            log.debug "resp data: ${resp.data}"
            log.debug "humidity: ${resp.data.main.humidity}"
        }
    } catch (e) {
        log.error "error: $e"
    }
}

def rqVolumeReset() {
    def params = [
        uri:  'http://192.168.2.249:5000',
        path: '/receiver/volume/reset/28'
    ]
    try {
        httpGet(params) {resp ->
            log.debug "resp data: ${resp.data}"
            log.debug "humidity: ${resp.data.main.humidity}"
        }
    } catch (e) {
        log.error "error: $e"
    }
}