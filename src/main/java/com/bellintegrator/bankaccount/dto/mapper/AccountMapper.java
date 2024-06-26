package com.bellintegrator.bankaccount.dto.mapper;

import com.bellintegrator.bankaccount.dto.AccountDTO;
import com.bellintegrator.bankaccount.model.Account;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface AccountMapper {

    @Mapping(source = "client.id", target = "clientId")
    AccountDTO accountToAccountDTO(Account account);

    @Mapping(source = "clientId", target = "client.id")
    @Mapping(target = "cards", ignore = true)
    Account accountDTOToAccount(AccountDTO accountDTO);

    List<AccountDTO> toAccountDTOList(List<Account> accounts);
    List<Account> toAccountList(List<AccountDTO> accountsDTO);
}

