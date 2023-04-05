package pl.lsobotka.green.code.interfaces.rest.transactions;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import pl.lsobotka.green.code.application.transactions.TransactionService;
import pl.lsobotka.green.code.interfaces.rest.transactions.dto.AccountDto;
import pl.lsobotka.green.code.interfaces.rest.transactions.dto.TransactionDto;

@RestController
@RequestMapping(path = "/transactions")
public class TransactionsController {

    private final TransactionService transactionService;

    public TransactionsController(final TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/report")
    @ResponseStatus(HttpStatus.OK)
    public List<AccountDto> report(@RequestBody final List<TransactionDto> transactions) {
        return transactionService.report(transactions);
    }
}
