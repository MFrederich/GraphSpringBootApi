package com.study.graphSpringBootApi.controller;

import com.study.graphSpringBootApi.domain.Client;
import com.study.graphSpringBootApi.entity.BankAccount;
import com.study.graphSpringBootApi.service.BankService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.data.method.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Map;

@Controller
@Slf4j
@AllArgsConstructor
public class AccountsController {

    private final BankService bankService;

    @QueryMapping
    List<BankAccount> accounts(@ContextValue String accountStatus) {
        log.info("Getting Accounts ");
        return bankService.getAccounts(accountStatus);
    }

    @QueryMapping
    BankAccount accountById(@Argument("accountId") Long accountId) {
        log.info("Getting Account " + accountId);
        return bankService.getAccountById(accountId);
    }

    @BatchMapping(field = "client", typeName = "BankAccountType")
    Map<BankAccount, Client> getClient(List<BankAccount> accounts) {
        log.info("Getting client for Accounts : " + accounts.size());
        return bankService.getClients(accounts);
    }

    @MutationMapping
    Boolean addAccount(@Argument("account") BankAccount account){
        log.info("save account: " + account);
        bankService.save(account);
        return true;
    }

    @MutationMapping
    BankAccount editAccount(@Argument("account") BankAccount account){
        log.info("Edit account: "+ account);
        return bankService.edit(account);
    }

    @MutationMapping
    Boolean deleteAccount(@Argument("accountId") Long accountId){
        log.info("delete account: "+ accountId);
        bankService.delete(accountId);
        return true;
    }
}
