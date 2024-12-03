package io.bootify.live_auction.model;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;

import io.bootify.live_auction.service.AuctionService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Map;
import org.springframework.web.servlet.HandlerMapping;


/**
 * Validate that the sellerId value isn't taken yet.
 */
@Target({ FIELD, METHOD, ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(
        validatedBy = AuctionSellerIdUnique.AuctionSellerIdUniqueValidator.class
)
public @interface AuctionSellerIdUnique {

    String message() default "{Exists.auction.sellerId}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    class AuctionSellerIdUniqueValidator implements ConstraintValidator<AuctionSellerIdUnique, Integer> {

        private final AuctionService auctionService;
        private final HttpServletRequest request;

        public AuctionSellerIdUniqueValidator(final AuctionService auctionService,
                final HttpServletRequest request) {
            this.auctionService = auctionService;
            this.request = request;
        }

        @Override
        public boolean isValid(final Integer value, final ConstraintValidatorContext cvContext) {
            if (value == null) {
                // no value present
                return true;
            }
            @SuppressWarnings("unchecked") final Map<String, String> pathVariables =
                    ((Map<String, String>)request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE));
            final String currentId = pathVariables.get("id");
            if (currentId != null && value.equals(auctionService.get(Integer.parseInt(currentId)).getSellerId())) {
                // value hasn't changed
                return true;
            }
            return !auctionService.sellerIdExists(value);
        }

    }

}
