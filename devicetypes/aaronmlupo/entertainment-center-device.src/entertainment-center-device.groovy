/**
 *  Entertainment Center Device
 *
 *  Copyright 2017 Aaron Lupo
 *
 *  Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License. You may obtain a copy of the License at:
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software distributed under the License is distributed
 *  on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License
 *  for the specific language governing permissions and limitations under the License.
 *
 */
metadata {
	definition (name: "Entertainment Center Device", namespace: "aaronmlupo", author: "Aaron Lupo") {
		// capability "Audio Mute"
		// capability "Audio Volume"
        capability "Switch"

		command "systemOn"
        command "systemOff"
		command "tvOn"
		command "tvOff"
		command "receiverOn"
		command "receiverOff"
		command "volumeZero"
        command "volumeUp"
        command "volumeDown"
        command "volumeSet"
        command "muteOn"
        command "muteOff"
        command "rokuHome"
        command "rokuUp"
        command "rokuBack"
        command "rokuLeft"
        command "rokuOk"
        command "rokuRight"
        command "rokuReturn"
        command "rokuDown"
        command "rokuStar"
        command "rokuRewind"
        command "rokuPlayPause"
        command "rokuFastforward"
        
        attribute "tvPowerStatus", "string"
        attribute "receiverPowerStatus", "string"
        attribute "systemPowerStatus", "string"
        attribute "volume", "number"
        attribute "muteStatus", "string"
        attribute "dummy", "string"
	}

	simulator {
		// TODO: define status and reply messages here
	}

	tiles(scale: 2) {
        // Power Control Switches
        // standardTile("systemSwitch", "systemPowerStatus", width: 1, height: 1, canChangeIcon: true) {
        standardTile("systemSwitch", "device.switch", width: 1, height: 1, canChangeIcon: true) {
            state "off", label: 'System: off', action: "systemOn",
                  icon: "st.switches.switch.off", backgroundColor: "#ffffff"
            state "on", label: 'System: on', action: "systemOff",
                  icon: "st.switches.switch.on", backgroundColor: "#00a0dc"
			// state "partial", label: 'System: partial', action: "systemOff",
            //	  icon: "st.switches.switch.off", backgroundColor: "#ffffff"
        }
        standardTile("tvSwitch", "tvPowerStatus", width: 1, height: 1, canChangeIcon: true) {
            state "off", label: 'TV: off', action: "tvOn",
                  icon: "st.switches.switch.off", backgroundColor: "#ffffff"
            state "on", label: 'TV: on', action: "tvOff",
                  icon: "st.switches.switch.on", backgroundColor: "#00a0dc"
        }
        standardTile("receiverSwitch", "receiverPowerStatus", width: 1, height: 1, canChangeIcon: true) {
            state "off", label: 'Receiver: off', action: "receiverOn",
                  icon: "st.switches.switch.off", backgroundColor: "#ffffff"
            state "on", label: 'Receiver: on', action: "receiverOff",
                  icon: "st.switches.switch.on", backgroundColor: "#00a0dc"
        }

		// Audio Controls
        standardTile("volUpSwitch", "dummy", width: 1, height: 1, canChangeIcon: true) {
            state "press", label: 'Vol+', action: "volumeUp",
                  icon: "st.switches.switch.off", backgroundColor: "#ffffff", defaultState: true
        }
        standardTile("muteSwitch", "muteStatus", width: 1, height: 1, canChangeIcon: true) {
            state "off", label: 'Mute: off', action: "muteOn",
                  icon: "st.switches.switch.off", backgroundColor: "#ffffff"
            state "on", label: 'Mute: on', action: "muteOff",
                  icon: "st.switches.switch.on", backgroundColor: "#00a0dc"
        }
        standardTile("volDownSwitch", "dummy", width: 1, height: 1, canChangeIcon: true) {
            state "press", label: 'Vol-', action: "volumeDown",
                  icon: "st.switches.switch.off", backgroundColor: "#ffffff", defaultState: true
        }
        //controlTile("volumeSliderControl", "volume", "slider", height: 1,
        //             width: 4, inactiveLabel: false, range:"(0..60)") {
        //    state "volume", action:"switch level.setLevel"
        //}
        
        // Roku Controls
        standardTile("btnRokuBack", "dummy", width: 2, height: 2, canChangeIcon: false) {
        	state "press", label: "Back", action: "rokuBack",
            	icon: "", backgroundColor: "#ffffff", defaultState: true
        }
        standardTile("btnRokuUp", "dummy", width: 2, height: 2, canChangeIcon: false) {
        	state "press", label: "Up", action: "rokuUp",
            	icon: "", backgroundColor: "#ffffff", defaultState: true
        }
        standardTile("btnRokuHome", "dummy", width: 2, height: 2, canChangeIcon: false) {
        	state "press", label: "Home", action: "rokuHome",
            	icon: "", backgroundColor: "#ffffff", defaultState: true
        }
        standardTile("btnRokuLeft", "dummy", width: 2, height: 2, canChangeIcon: false) {
        	state "press", label: "Left", action: "rokuLeft",
            	icon: "", backgroundColor: "#ffffff", defaultState: true
        }
        standardTile("btnRokuOk", "dummy", width: 2, height: 2, canChangeIcon: false) {
        	state "press", label: "Ok", action: "rokuOk",
            	icon: "", backgroundColor: "#ffffff", defaultState: true
        }
        standardTile("btnRokuRight", "dummy", width: 2, height: 2, canChangeIcon: false) {
        	state "press", label: "Right", action: "rokuRight",
            	icon: "", backgroundColor: "#ffffff", defaultState: true
        }
        standardTile("btnRokuReturn", "dummy", width: 2, height: 2, canChangeIcon: false) {
        	state "press", label: "Return", action: "rokuReturn",
            	icon: "", backgroundColor: "#ffffff", defaultState: true
        }
        standardTile("btnRokuDown", "dummy", width: 2, height: 2, canChangeIcon: false) {
        	state "press", label: "Down", action: "rokuDown",
            	icon: "", backgroundColor: "#ffffff", defaultState: true
        }
        standardTile("btnRokuStar", "dummy", width: 2, height: 2, canChangeIcon: false) {
        	state "press", label: "Star", action: "rokuStar",
            	icon: "", backgroundColor: "#ffffff", defaultState: true
        }
        standardTile("btnRokuRewind", "dummy", width: 2, height: 2, canChangeIcon: false) {
        	state "press", label: "Rewind", action: "rokuRewind",
            	icon: "", backgroundColor: "#ffffff", defaultState: true
        }
        standardTile("btnRokuPlayPause", "dummy", width: 2, height: 2, canChangeIcon: false) {
        	state "press", label: "Play", action: "rokuPlayPause",
            	icon: "", backgroundColor: "#ffffff", defaultState: true
        }
        standardTile("btnRokuFastforward", "dummy", width: 2, height: 2, canChangeIcon: false) {
        	state "press", label: "Fastforward", action: "rokuFastforward",
            	icon: "", backgroundColor: "#ffffff", defaultState: true
        }







		// value tile (read only)
        // valueTile("power", "device.power", decoration: "flat", width: 2, height: 2) {
        //     state "power", label:'${currentValue} Watts'
        // }

        // the "switch" tile will appear in the Things view
        main("systemSwitch")

        // the "switch" and "power" tiles will appear in the Device Details
        // view (order is left-to-right, top-to-bottom)
        // details(["switch", "power"])
        details(["systemSwitch", "tvSwitch", "receiverSwitch", "volUpSwitch", "muteSwitch", "volDownSwitch", "btnRokuBack", "btnRokuUp", "btnRokuHome", "btnRokuLeft", "btnRokuOk", "btnRokuRight", "btnRokuReturn", "btnRokuDown", "btnRokuStar", "btnRokuRewind", "btnRokuPlayPause", "btnRokuFastforward"])
    }
    
    preferences {
    	input name: "ip", type: "text", title: "IP Address", description: "Address of Raspberry Pi controller device", required: true, displayDuringSetup: true
        input name: "port", type: "text", title: "Port", description: "Port number of Raspberry Pi controller device", required: true, displayDuringSetup: true
    }
}

// parse events into attributes
def parse(String description) {
	log.debug "Parsing '${description}'"
	// TODO: handle 'mute' attribute
	// TODO: handle 'volume' attribute
    def msg = parseLanMessage(description)

    def headersAsString = msg.header // => headers as a string
    def headerMap = msg.headers      // => headers as a Map
    def body = msg.body              // => request body as a string
    def status = msg.status          // => http status code of the response
    def json = msg.json              // => any JSON included in response body, as a data structure of lists and maps
    def xml = msg.xml                // => any XML included in response body, as a document tree structure
    def data = msg.data              // => either JSON or XML in response body (whichever is specified by content-type header in response)

	log.debug "Msg body = $body"

}

// handle commands
//def setMute() {
//	log.debug "Executing 'setMute'"
//	// TODO: handle 'setMute' command
//}

//def mute() {
//	log.debug "Executing 'mute'"
//	// TODO: handle 'mute' command
//}

//def unmute() {
//	log.debug "Executing 'unmute'"
//	// TODO: handle 'unmute' command
//}

//def setVolume() {
//	log.debug "Executing 'setVolume'"
//	// TODO: handle 'setVolume' command
//}

//def volumeUp() {
//	log.debug "Executing 'volumeUp'"
//	// TODO: handle 'volumeUp' command
//}

//def volumeDown() {
//	log.debug "Executing 'volumeDown'"
//	// TODO: handle 'volumeDown' command
//}

def muteOn() {
	log.debug "Executing 'muteOn'"
    log.debug "Sending to ${ip}:${port}"
	// TODO: handle 'receiverOn' command
    // device.deviceNetworkId = "${ip}:${port}"
    def result = new physicalgraph.device.HubAction(
        method: "GET",
        path: "/receiver/volume/mute",
        headers: [
            HOST: "${ip}:${port}"],
            device.deviceNetworkId,
            [callback: commandResponse]
    )
    return result
}

def muteOff() {
	log.debug "Executing 'muteOff'"
    log.debug "Sending to ${ip}:${port}"
	// TODO: handle 'receiverOn' command
    // device.deviceNetworkId = "${ip}:${port}"
    def result = new physicalgraph.device.HubAction(
        method: "GET",
        path: "/receiver/volume/mute",
        headers: [
            HOST: "${ip}:${port}"],
            device.deviceNetworkId,
            [callback: commandResponse]
    )
    return result
}

def rokuBack() {
	log.debug "Executing 'rokuBack'"
    log.debug "Sending to ${ip}:${port}"
	// TODO: handle 'receiverOn' command
    // device.deviceNetworkId = "${ip}:${port}"
    def result = new physicalgraph.device.HubAction(
        method: "GET",
        path: "/roku/button/back",
        headers: [
            HOST: "${ip}:${port}"],
            device.deviceNetworkId,
            [callback: commandResponse]
    )
    return result
}

def rokuUp() {
	log.debug "Executing 'rokuUp'"
    log.debug "Sending to ${ip}:${port}"
	// TODO: handle 'receiverOn' command
    // device.deviceNetworkId = "${ip}:${port}"
    def result = new physicalgraph.device.HubAction(
        method: "GET",
        path: "/roku/button/up",
        headers: [
            HOST: "${ip}:${port}"],
            device.deviceNetworkId,
            [callback: commandResponse]
    )
    return result
}

def rokuHome() {
	log.debug "Executing 'rokuHome'"
    log.debug "Sending to ${ip}:${port}"
	// TODO: handle 'receiverOn' command
    // device.deviceNetworkId = "${ip}:${port}"
    def result = new physicalgraph.device.HubAction(
        method: "GET",
        path: "/roku/button/home",
        headers: [
            HOST: "${ip}:${port}"],
            device.deviceNetworkId,
            [callback: commandResponse]
    )
    return result
}

def rokuLeft() {
	log.debug "Executing 'rokuLeft'"
    log.debug "Sending to ${ip}:${port}"
	// TODO: handle 'receiverOn' command
    // device.deviceNetworkId = "${ip}:${port}"
    def result = new physicalgraph.device.HubAction(
        method: "GET",
        path: "/roku/button/left",
        headers: [
            HOST: "${ip}:${port}"],
            device.deviceNetworkId,
            [callback: commandResponse]
    )
    return result
}

def rokuOk() {
	log.debug "Executing 'rokuOk'"
    log.debug "Sending to ${ip}:${port}"
	// TODO: handle 'receiverOn' command
    // device.deviceNetworkId = "${ip}:${port}"
    def result = new physicalgraph.device.HubAction(
        method: "GET",
        path: "/roku/button/ok",
        headers: [
            HOST: "${ip}:${port}"],
            device.deviceNetworkId,
            [callback: commandResponse]
    )
    return result
}

def rokuRight() {
	log.debug "Executing 'rokuRight'"
    log.debug "Sending to ${ip}:${port}"
	// TODO: handle 'receiverOn' command
    // device.deviceNetworkId = "${ip}:${port}"
    def result = new physicalgraph.device.HubAction(
        method: "GET",
        path: "/roku/button/right",
        headers: [
            HOST: "${ip}:${port}"],
            device.deviceNetworkId,
            [callback: commandResponse]
    )
    return result
}

def rokuReturn() {
	log.debug "Executing 'rokuReturn'"
    log.debug "Sending to ${ip}:${port}"
	// TODO: handle 'receiverOn' command
    // device.deviceNetworkId = "${ip}:${port}"
    def result = new physicalgraph.device.HubAction(
        method: "GET",
        path: "/roku/button/return",
        headers: [
            HOST: "${ip}:${port}"],
            device.deviceNetworkId,
            [callback: commandResponse]
    )
    return result
}

def rokuDown() {
	log.debug "Executing 'rokuDown'"
    log.debug "Sending to ${ip}:${port}"
	// TODO: handle 'receiverOn' command
    // device.deviceNetworkId = "${ip}:${port}"
    def result = new physicalgraph.device.HubAction(
        method: "GET",
        path: "/roku/button/down",
        headers: [
            HOST: "${ip}:${port}"],
            device.deviceNetworkId,
            [callback: commandResponse]
    )
    return result
}

def rokuStar() {
	log.debug "Executing 'rokuStar'"
    log.debug "Sending to ${ip}:${port}"
	// TODO: handle 'receiverOn' command
    // device.deviceNetworkId = "${ip}:${port}"
    def result = new physicalgraph.device.HubAction(
        method: "GET",
        path: "/roku/button/star",
        headers: [
            HOST: "${ip}:${port}"],
            device.deviceNetworkId,
            [callback: commandResponse]
    )
    return result
}

def rokuRewind() {
	log.debug "Executing 'rokuRewind'"
    log.debug "Sending to ${ip}:${port}"
	// TODO: handle 'receiverOn' command
    // device.deviceNetworkId = "${ip}:${port}"
    def result = new physicalgraph.device.HubAction(
        method: "GET",
        path: "/roku/button/rewind",
        headers: [
            HOST: "${ip}:${port}"],
            device.deviceNetworkId,
            [callback: commandResponse]
    )
    return result
}

def rokuPlayPause() {
	log.debug "Executing 'rokuPlayPause'"
    log.debug "Sending to ${ip}:${port}"
	// TODO: handle 'receiverOn' command
    // device.deviceNetworkId = "${ip}:${port}"
    def result = new physicalgraph.device.HubAction(
        method: "GET",
        path: "/roku/button/playpause",
        headers: [
            HOST: "${ip}:${port}"],
            device.deviceNetworkId,
            [callback: commandResponse]
    )
    return result
}

def rokuFastforward() {
	log.debug "Executing 'rokuFastforward'"
    log.debug "Sending to ${ip}:${port}"
	// TODO: handle 'receiverOn' command
    // device.deviceNetworkId = "${ip}:${port}"
    def result = new physicalgraph.device.HubAction(
        method: "GET",
        path: "/roku/button/fastforward",
        headers: [
            HOST: "${ip}:${port}"],
            device.deviceNetworkId,
            [callback: commandResponse]
    )
    return result
}

def tvOn() {
	log.debug "Executing 'tvOn'"
    log.debug "Sending to ${ip}:${port}"
	// TODO: handle 'receiverOn' command
    // device.deviceNetworkId = "${ip}:${port}"
    def result = new physicalgraph.device.HubAction(
        method: "GET",
        path: "/tv/power/on",
        headers: [
            HOST: "${ip}:${port}"],
            device.deviceNetworkId,
            [callback: commandResponse]
    )
    return result
}

def tvOff() {
	log.debug "Executing 'tvOff'"
    log.debug "Sending to ${ip}:${port}"
	// TODO: handle 'receiverOn' command
    // device.deviceNetworkId = "${ip}:${port}"
    def result = new physicalgraph.device.HubAction(
        method: "GET",
        path: "/tv/power/off",
        headers: [
            HOST: "${ip}:${port}"],
            device.deviceNetworkId,
            [callback: commandResponse]
    )
    return result
}

def receiverOn() {
	log.debug "Executing 'receiverOn'"
    log.debug "Sending to ${ip}:${port}"
	// TODO: handle 'receiverOn' command
    // device.deviceNetworkId = "${ip}:${port}"
    def result = new physicalgraph.device.HubAction(
        method: "GET",
        path: "/receiver/power/on",
        headers: [
            HOST: "${ip}:${port}"],
            device.deviceNetworkId,
            [callback: commandResponse]
    )
    return result
}

def receiverOff() {
	log.debug "Executing 'receiverOff'"
    log.debug "Sending to ${ip}:${port}"
	// TODO: handle 'receiverOff' command
    def result = new physicalgraph.device.HubAction(
        method: "GET",
        path: "/receiver/power/off",
        headers: [
            HOST: "${ip}:${port}"],
            device.deviceNetworkId,
            [callback: commandResponse]
    )
    return result
}

def systemOn() {
	tvOn()
    receiverOn()
}

def systemOff() {
	receiverOff()
    tvOff()
}

def zeroVolume() {
	log.debug "Executing 'zeroVolume'"
	// TODO: handle 'zeroVolume' command
}

def commandResponse(response){
	log.debug(response)
    log.debug(response.headers)
    log.debug(response.json)
    log.debug(response.body)
    
    def msgJson = parseJson(response.body)
    log.debug("json ... $msgJson.message")
    
    if (msgJson.message == "receiver power off") {
    	sendEvent(name: "receiverPowerStatus", value: "off", isStateChange: true)
        // device.currentState("receiverSwitch")="off"
    }
    
    if (msgJson.message == "receiver power on") {
    	sendEvent(name: "receiverPowerStatus", value: "on", isStateChange: true)
    }
    
    if (msgJson.message == "tv power off") {
    	sendEvent(name: "tvPowerStatus", value: "off", isStateChange: true)
    }
    
    if (msgJson.message == "tv power on") {
    	sendEvent(name: "tvPowerStatus", value: "on", isStateChange: true)
    }
    
    if ((device.currentState("receiverPowerStatus")?.value == "on") && (device.currentState("tvPowerStatus")?.value == "on")) {
    	sendEvent(name: "switch", value: "on", isStateChange: true)
    // } else if ((device.currentState("receiverPowerStatus")?.value == "on") && (device.currentState("tvPowerStatus")?.value == "off")) {
    //	sendEvent(name: "systemPowerStatus", value: "partial", isStateChange: true)
    //} else if ((device.currentState("receiverPowerStatus")?.value == "off") && (device.currentState("tvPowerStatus")?.value == "on")) {
    //	sendEvent(name: "systemPowerStatus", value: "partial", isStateChange: true)
    } else {
    	sendEvent(name: "switch", value: "off", isStateChange: true)
    }
    
    if (msgJson.message == "volume mute on") {
    	sendEvent(name: "muteStatus", value: "on", isStateChange: true)
    }
    if (msgJson.message == "volume mute off") {
    	sendEvent(name: "muteStatus", value: "off", isStateChange: true)
    }
}
