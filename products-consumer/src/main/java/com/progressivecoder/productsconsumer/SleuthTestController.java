package com.progressivecoder.productsconsumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
public class SleuthTestController {

    Logger logger = Logger.getLogger("SleuthTestController");

    @Autowired
    SleuthService sleuthService;

    @GetMapping("/wake-up-sleuth")
    public String wakeUpSleuth(){
        logger.info("Wake Up Sleuth");
        return "Logging success";
    }


    @GetMapping("/same-span-sleuth")
    public String sameSpanSleuth(){
        logger.info("Same span work initiated");
        sleuthService.sameSpanWork();
        return "Logging success for same span";
    }

    @GetMapping("/new-span-sleuth")
    public String newSpanSleuth(){
        logger.info("New span work initiated");
        sleuthService.newSpanWork();
        return "Logging success for new span";
    }
}
