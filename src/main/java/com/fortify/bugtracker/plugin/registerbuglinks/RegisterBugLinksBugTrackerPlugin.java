/*******************************************************************************
 * (c) Copyright 2020 Micro Focus or one of its affiliates, a Micro Focus company
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a 
 * copy of this software and associated documentation files (the 
 * "Software"), to deal in the Software without restriction, including without 
 * limitation the rights to use, copy, modify, merge, publish, distribute, 
 * sublicense, and/or sell copies of the Software, and to permit persons to 
 * whom the Software is furnished to do so, subject to the following 
 * conditions:
 * 
 * The above copyright notice and this permission notice shall be included 
 * in all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY 
 * KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE 
 * WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR 
 * PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE 
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, 
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF 
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN 
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS 
 * IN THE SOFTWARE.
 ******************************************************************************/
package com.fortify.bugtracker.plugin.registerbuglinks;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.fortify.pub.bugtracker.plugin.AbstractBatchBugTrackerPlugin;
import com.fortify.pub.bugtracker.plugin.BugTrackerPluginImplementation;
import com.fortify.pub.bugtracker.support.Bug;
import com.fortify.pub.bugtracker.support.BugParam;
import com.fortify.pub.bugtracker.support.BugParamText;
import com.fortify.pub.bugtracker.support.BugSubmission;
import com.fortify.pub.bugtracker.support.BugTrackerConfig;
import com.fortify.pub.bugtracker.support.IssueDetail;
import com.fortify.pub.bugtracker.support.MultiIssueBugSubmission;
import com.fortify.pub.bugtracker.support.UserAuthenticationStore;

@BugTrackerPluginImplementation
public class RegisterBugLinksBugTrackerPlugin extends AbstractBatchBugTrackerPlugin {
	private static final String LINK_IDENTIFIER = "existingBugLink"; // We use this bugparam id for backward compatibility with 'Add Existing Bugs' 

	public List<BugParam> getBatchBugParameters(UserAuthenticationStore paramUserAuthenticationStore) {
		BugParam bp = new BugParamText();
		bp.setIdentifier(LINK_IDENTIFIER);
		bp.setDisplayLabel("Link");
		bp.setRequired(true);
		return Arrays.asList(bp);
	}

	public List<BugParam> onBatchBugParameterChange(String paramString, List<BugParam> paramList, UserAuthenticationStore paramUserAuthenticationStore) {
		return getBatchBugParameters(paramUserAuthenticationStore);
	}

	public Bug fileMultiIssueBug(MultiIssueBugSubmission paramMultiIssueBugSubmission, UserAuthenticationStore paramUserAuthenticationStore) {
		String link = paramMultiIssueBugSubmission.getParams().get(LINK_IDENTIFIER);
		return new Bug(link, "");
	}

	public boolean isBugOpen(Bug paramBug, UserAuthenticationStore paramUserAuthenticationStore) {
		return true;
	}

	public boolean isBugClosed(Bug paramBug, UserAuthenticationStore paramUserAuthenticationStore) {
		return false;
	}

	public boolean isBugClosedAndCanReOpen(Bug paramBug, UserAuthenticationStore paramUserAuthenticationStore) {
		return false;
	}

	public void reOpenBug(Bug paramBug, String paramString, UserAuthenticationStore paramUserAuthenticationStore) {
		throw new IllegalAccessError("Not implemented");
	}

	public void addCommentToBug(Bug paramBug, String paramString, UserAuthenticationStore paramUserAuthenticationStore) {
		throw new IllegalAccessError("Not implemented");
	}

	public Bug fetchBugDetails(String link, UserAuthenticationStore paramUserAuthenticationStore) {
		return new Bug(link, "");
	}

	public Bug fileBug(BugSubmission paramBugSubmission, UserAuthenticationStore paramUserAuthenticationStore) {
		String link = paramBugSubmission.getParams().get(LINK_IDENTIFIER);
		return new Bug(link, "");
	}

	public String getBugDeepLink(String link) {
		return link;
	}

	public List<BugParam> getBugParameters(IssueDetail paramIssueDetail, UserAuthenticationStore paramUserAuthenticationStore) {
		return getBatchBugParameters(paramUserAuthenticationStore);
	}

	public List<BugTrackerConfig> getConfiguration() {
		return Arrays.asList(new BugTrackerConfig[]{});
	}

	public String getLongDisplayName() {
		return getShortDisplayName();
	}

	public String getShortDisplayName() {
		return "Register Bug Links";
	}

	public List<BugParam> onParameterChange(IssueDetail paramIssueDetail, String paramString, List<BugParam> paramList, UserAuthenticationStore paramUserAuthenticationStore) {
		return getBatchBugParameters(paramUserAuthenticationStore);
	}

	public boolean requiresAuthentication() {
		return false;
	}

	public void setConfiguration(Map<String, String> paramMap) {
		// Nothing to do
	}

	public void testConfiguration(UserAuthenticationStore paramUserAuthenticationStore) {
		// Nothing to do
	}

	public void validateCredentials(UserAuthenticationStore paramUserAuthenticationStore) {
		// Nothing to do
	}

}
