package com.leeharkness.mimir.mimiractions;

import com.google.common.collect.ImmutableList;
import com.leeharkness.mimir.ActionResult;
import com.leeharkness.mimir.MimirUIElements;
import com.leeharkness.mimir.datalayer.ContactDao;
import com.leeharkness.mimir.mimirsupport.MimirSessionContext;
import com.leeharkness.mimir.model.MimirContact;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindContactAction extends MimirLoggedInRequiredBaseAction {

    private ContactDao contactDao;

    @Inject
    public FindContactAction(ContactDao contactDao) {
        this.contactDao = contactDao;
    }

    @Override
    protected ActionResult actionSpecificHandle(String[] input, MimirUIElements mimirUIElements,
                                                MimirSessionContext mimirSessionContext) {

        List<MimirContact> contacts = contactDao.searchContacts(input[1]);
        Map<String, Object> data = new HashMap<>();
        data.put("contacts", contacts);

        return ActionResult.builder()
                .resultMap(data)
                .build();

    }

    @Override
    public List<String> handles() {
        return ImmutableList.of("fc", "findcontact");
    }
}
