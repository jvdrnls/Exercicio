package application;

import entities.Contract;
import entities.Installment;
import services.ContractService;
import services.PaypalService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        System.out.println("Entre os dados do contrato: ");
        System.out.print("Numero: ");
        Integer contractNumber = sc.nextInt();
        System.out.println("Data (dd/MM/yyyy): ");
        String contractDateString = sc.next();
        LocalDate contractDate = LocalDate.parse(contractDateString, fmt);
        System.out.println("Valor do contrato: ");
        Double contractTotalValue = sc.nextDouble();
        System.out.println("Entre com o numero de parcelas: ");
        int numberOfInstallments = sc.nextInt();
        
        List<Installment> installments = new ArrayList<Installment>();

        Contract contract = new Contract(contractNumber, contractDate, contractTotalValue, installments);

        ContractService serviceContract = new ContractService(new PaypalService());

        serviceContract.processContract(contract, numberOfInstallments);


        sc.close();
    }
}
