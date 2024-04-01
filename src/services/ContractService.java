package services;

import entities.Contract;
import entities.Installment;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ContractService {


    private OnlinePaymentService onlinePaymentService;

    public ContractService(OnlinePaymentService onlinePaymentService) {
        this.onlinePaymentService = onlinePaymentService;
    }
    public void processContract (Contract contract, Integer months){

        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        double installmentPriceWithoutInterestAndFees = contract.getTotalValue() / months;
        LocalDate installmentDate = contract.getDate();

        System.out.println("Parcelas:");
        for (int n = 1; n <= months; n++) {
            double installmentPriceWithInterest = onlinePaymentService.interest(installmentPriceWithoutInterestAndFees, n);
            double installmentPriceWithInterestAndFees = onlinePaymentService.paymentFee(installmentPriceWithInterest);

            installmentDate = contract.getDate().plusMonths(n);
            Installment installment = new Installment(installmentDate, installmentPriceWithInterestAndFees);
            contract.getInstallments().add(installment);

            System.out.println(contract.getInstallments().get(n-1).getDueDate().format(fmt) + " - " + String.format("%.2f", installmentPriceWithInterestAndFees));
        }


    }
}
