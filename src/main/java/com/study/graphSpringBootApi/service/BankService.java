package com.study.graphSpringBootApi.service;

import com.study.graphSpringBootApi.domain.Client;
import com.study.graphSpringBootApi.entity.BankAccount;
import com.study.graphSpringBootApi.exceptions.AccountNotFoundException;
import com.study.graphSpringBootApi.exceptions.ClientNotFoundException;
import com.study.graphSpringBootApi.repository.BankAccountRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class BankService {

    private static List<Client> clients = Arrays.asList(
            new Client(100L, "John", "T.", "Doe", "US"),
            new Client(101L, "Emma", "B.", "Smith", "CA"),
            new Client(102L, "James", "R.", "Brown", "IN"),
            new Client(103L, "Olivia", "S.", "Johnson", "UK"),
            new Client(104L, "William", "K.", "Jones", "SG")
    );

    private final BankAccountRepository bankAccountRepository;

    public List<BankAccount> getAccounts(String accountStatus) {
        return bankAccountRepository.findByStatus(accountStatus);
    }

    public BankAccount getAccountById(Long accountId) {
        return bankAccountRepository.findById(accountId).orElseThrow(() -> new AccountNotFoundException(""));
    }

    public Map<BankAccount, Client> getClients(List<BankAccount> accounts) {
        return accounts.stream().collect(Collectors.toMap(
                account -> account,
                account -> getClientByAccountId(account.getClientId())
        ));
    }

    private Client getClientByAccountId(Long id) {
        return clients.stream()
                .filter(client -> client.getId().equals(id))
                .findFirst().orElse(null);
    }

    public void save(BankAccount bankAccount) {
        if (validClient(bankAccount)) {
            bankAccountRepository.save(bankAccount);
        } else {
            throw new ClientNotFoundException("client not found  id:" + bankAccount.getClientId());
        }
    }

    public BankAccount edit(BankAccount bankAccount) {
        if (validClient(bankAccount)) {
            bankAccountRepository.save(bankAccount);
            return bankAccount;
        }
        throw new ClientNotFoundException("");
    }

    public Boolean delete(Long accountId) {
        try {
            bankAccountRepository.delete(getAccountById(accountId));
            return true;
        } catch (AccountNotFoundException ex) {
            log.error("error deleting %s", accountId);
            return false;
        }
    }

    private boolean validClient(BankAccount account) {
        return clients.stream()
                .filter(client -> client.getId().equals(account.getClientId())).findAny().isPresent();
    }
}
