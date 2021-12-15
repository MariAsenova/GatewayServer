package via.sep4.data.webapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import via.sep4.data.webapi.model.Device;
import via.sep4.data.webapi.service.device.DeviceService;
import via.sep4.data.webapi.service.remote.RemoteService;
import via.sep4.data.webapi.util.ApiKeyUtil;

@RestController
@RequestMapping("/remote")
public class RemoteController {

    @Autowired
    private RemoteService remoteService;

    @Autowired
    private ApiKeyUtil util;

    @Autowired
    private DeviceService deviceService;


    @PostMapping("/{eui}/window")
    public ResponseEntity setWindowValue(@RequestHeader("api-key") String apiKey, @PathVariable String eui, @RequestParam int commandPercentage) {
        try {
            util.checkApi(apiKey);
            Device device = deviceService.findDeviceByEUI(eui);
            remoteService.setWindow(device.getEUI(), commandPercentage);
            return new ResponseEntity<>("Successful", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/{eui}/water")
    public ResponseEntity setWaterValue(@RequestHeader("api-key") String apiKey, @PathVariable String eui, @RequestParam int waterValue) {
        try {
            util.checkApi(apiKey);
            Device device = deviceService.findDeviceByEUI(eui);
            remoteService.setWater(device.getEUI(), waterValue);
            return new ResponseEntity<>("Successful", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }

    }

    @PostMapping("/{eui}/light")
    public ResponseEntity setLightValue(@RequestHeader("api-key") String apiKey, @PathVariable String eui, @RequestParam int lightValue) {
        try {
            util.checkApi(apiKey);
            Device device = deviceService.findDeviceByEUI(eui);
            remoteService.setLight(device.getEUI(), lightValue);
            return new ResponseEntity<>("Successful", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }

    }
}
