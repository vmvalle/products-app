package com.vmvalle.productsapp.infrastructure.apirest;

import com.vmvalle.productsapp.infrastructure.apirest.dto.PriceResponse;
import com.vmvalle.productsapp.domain.entity.Price;
import com.vmvalle.productsapp.infrastructure.apirest.exception.ErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;

@RequestMapping("/products")
@Tag(name = "Products")
public interface ProductController {

    /**
     * GET endpoint for calculate price by product identifier, brand identifier and selected date.
     *
     * @param productId Product identifier
     * @param brandId Brand identifier
     * @param selectedDate Date selected for price.
     * @return Price data.
     */
    @Operation(summary = "Find Product by id, brand and selected date")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(schema = @Schema(implementation = Price.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Price not found", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping(value = "/{productId}", produces = "application/json")
    PriceResponse getPriceProductByBrandInSelectedDate(
            @Parameter(description = "ID of product", example = "35455", required = true) @PathVariable Long productId,
            @Parameter(description = "ID of brand", example = "1", required = true) @RequestParam Integer brandId,
            @Parameter(description = "Selected date for get price", example = "2020-06-14T10:00:00", required = true)
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime selectedDate);

}
