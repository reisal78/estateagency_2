package estateagency.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;


@Component
public class ReservedService {

    private static final Logger log = LoggerFactory.getLogger(ReservedService.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Autowired
    private ContractsSalesService contractsSalesService;
    @Autowired
    private ContractsRentsService contractsRentsService;


    @Scheduled(fixedRate = 5000)
    public void reportCurrentTime() {
       contractsSalesService.removeReserve();
       contractsRentsService.removeReserve();
    }

}
