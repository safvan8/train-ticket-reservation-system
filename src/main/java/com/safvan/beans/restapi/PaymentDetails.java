package com.safvan.beans.restapi;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents payment details for a booking request.
 * 
 * @author Safvan
 * @version 2.0
 * @since 2.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDetails {

	/**
	 * The payment method selected for the booking (e.g., Credit Card, Debit Card).
	 */
	private String paymentMethod;

	/**
	 * The card number associated with the payment
	 */
	private String cardNumber;

	/**
	 * The expiry date of the payment card (in the format MM/YY).
	 */
	private String expiryDate;

	/**
	 * The Card Verification Value (CVV) security code of the payment card.
	 */
	private String cvv;
}
