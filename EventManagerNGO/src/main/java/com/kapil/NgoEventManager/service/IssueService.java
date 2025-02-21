package com.kapil.NgoEventManager.service;

import com.kapil.NgoEventManager.modal.Issue;
import com.kapil.NgoEventManager.modal.User;
import com.kapil.NgoEventManager.request.IssueRequest;
import jdk.jshell.spi.ExecutionControl;

import java.util.List;
import java.util.Optional;

public interface IssueService {
    Issue getIssueById(Long issueId) throws Exception;

    List<Issue> getIssueByProjectId(Long projectId) throws Exception;

    Issue createIssue(IssueRequest issue, User user) throws Exception;

    void deleteIssue(Long issueId, Long userid) throws Exception;

    Issue addUserToIssue(Long issueId, Long userId) throws Exception;

    Issue updateStatus(Long issueId, String status) throws Exception;

}
