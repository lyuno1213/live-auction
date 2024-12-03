package io.bootify.live_auction.rest;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HomeResource {

    @GetMapping("/home")
    public RepresentationModel<?> index() {
        return RepresentationModel.of(null);
    }

}
