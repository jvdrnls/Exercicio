package services;

public class PaypalService implements OnlinePaymentService {

    @Override
    public Double interest(Double amount, Integer months) {
        return amount + (1.0/100 * amount) * months;
    }

    @Override
    public Double paymentFee(Double amount) {
        return amount + (2.0/100 * amount);
    }

}
