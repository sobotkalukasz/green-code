package pl.lsobotka.green.code.application.transactions;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import pl.lsobotka.green.code.interfaces.rest.transactions.dto.AccountDto;
import pl.lsobotka.green.code.interfaces.rest.transactions.dto.TransactionDto;

@Service
public class TransactionService {

    public List<AccountDto> report(final List<TransactionDto> transactions) {
        return TransactionProcessor.process(transactions).stream().map(AccountDto::from).collect(Collectors.toList());
    }
}
