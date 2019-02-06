package com.ecommerce.fi.rest;

import com.stripe.exception.*;
import com.stripe.model.Transfer;
import com.ecommerce.fi.service.TransferService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.ws.rs.core.Response;
import java.math.BigDecimal;

/**
 * @author Andrey Shchagin on 27.04.17.
 *
 */
@RestController
@Api(value = "/fin", description = "Financial transactions controller")
@RequestMapping("/fin")
public class TransferController {
    @Autowired
    private TransferService stripeService;
    Logger logger = LoggerFactory.getLogger(TransferController.class);
    RestTemplate restTemplate = new RestTemplate();

    @ApiOperation(value = "Make a transfer to supplier's account",
            notes = "Make a money transfer from dedicated account to ",
            response = Response.class)
    @RequestMapping(method = RequestMethod.PUT, path = "/transfer")
    public Response makeATransfer(
            @RequestParam BigDecimal account,
            @RequestParam String currency,
            @RequestParam String invoiceId,
            @RequestParam String clientId) throws StripeException {
        //Make monet transfer to managed account
        Transfer transfer = stripeService.transferToSubAccount(account, currency, clientId, invoiceId);
        return Response.ok(transfer).build();
    }
}
