package com.ecommerce.fi.rest;

import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.ecommerce.fi.service.OnBoardingService;
import com.ecommerce.fi.exceptions.GenericFiException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.Map;

/**
 * @author Andrey Shchagin on 27.04.17.
 *
 */
@RestController
@Api(value = "/fin/connect", description = "Financial module on-boarding controller")
@RequestMapping("/fin/connect")
public class OnBoardingController {

    @Autowired
    private OnBoardingService onBoardingService;
    @Value("${fi.client.id}")
    private String clientId;
    @Value("${fi.client.secret}")
    private String clientSecret;
    @Value("${fi.stripe.access-token}")
    private String accessTokenUrl;

    RestTemplate restTemplate = new RestTemplate();
    private final String CONNECT_URL = "https://connect.stripe.com/oauth/authorize?" +
            "response_type=code" +
            "&client_id=" + clientId +
            "&scope=read_write";

    @ApiOperation(value = "Connect supplier account to Stripe",
            notes = "Reference will be saved in DB")
    @RequestMapping(method = RequestMethod.GET, path = "/link_account")
    public void linkAccount(HttpServletResponse response, @RequestParam String externalSupplierId) throws IOException {
        //Make a money transfer to managed account
        response.sendRedirect(CONNECT_URL + "&state=" + externalSupplierId);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/accept")
    public Response handleRedirect(
            @RequestParam String scope,
            @ApiParam(value = "Supplier account reference", required = true) @RequestParam(name = "state") String externalSupplierAcct,
            @RequestParam String code
    ) throws GenericFiException {
        System.out.println(String.format("Scope: %s\nAuthCode: %s\nState: %s", scope, code, externalSupplierAcct));
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("client_secret", clientSecret);
        params.add("code", code);
        params.add("scope", scope);
        params.add("grant_type", "authorization_code");
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, new LinkedMultiValueMap<>());

        ResponseEntity<String> model =  restTemplate.exchange(accessTokenUrl, HttpMethod.POST, request, String.class);
        String bodyAsString = model.getBody();
        System.out.println(bodyAsString);
        Map<String, String> map = new GsonBuilder().create().fromJson(bodyAsString, new TypeToken<Map<String, String>>() { }.getType());
        //Save data to local DB
        onBoardingService.addSupplier(externalSupplierAcct, map);
        return Response.ok().build();
    }

}
