<x-tag-head>
<x-tag-meta http-equiv="X-UA-Compatible" content="IE=edge"/>

<x-tag-script language="JavaScript"><!--
<X-INCLUDE url="https://cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.0.0/build/highlight.min.js"/>
--></x-tag-script>

<x-tag-script language="JavaScript"><!--
<X-INCLUDE url="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js" />
--></x-tag-script>

<x-tag-script language="JavaScript"><!--
<X-INCLUDE url="${gradleHelpersLocation}/spa_readme.js" />
--></x-tag-script>

<x-tag-style><!--
<X-INCLUDE url="https://cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.0.0/build/styles/github.min.css" />
--></x-tag-style>

<x-tag-style><!--
<X-INCLUDE url="${gradleHelpersLocation}/spa_readme.css" />
--></x-tag-style>
</x-tag-head>

# Fortify SSC Bug Tracker Plugin for registering existing bug links

## Introduction

This Fortify SSC bug tracker plugin allows for registering bug links, allowing users to click the 'View Bug' button to visit these links.
This is mainly useful in the following situations:

* If users are manually creating bugs for some vulnerabilities, they can manually add the bug link to the corresponding vulnerabilities
* If users want to link to some other information related to specific vulnerabilities, like internal guidelines for remediating such vulnerabilities
* If bugs are submitted through other means than an SSC bug tracker plugin, for example using [FortifyBugTrackerUtility](https://github.com/fortify-ps/FortifyBugTrackerUtility)

### Warning

Enabling this plugin will allow users to add arbitrary, potentially malicious bug links in SSC. As a non-harmful example, try entering `javascript:alert('You have been hacked')` as a bug link, and then clicking the `View Bug` button. You should make sure that only trusted users have the `Submit Bugs` permission.

### Related Links

* **Downloads**: https://github.com/fortify-ps/fortify-ssc-bugtracker-register-buglinks/releases
    * _Development releases may be unstable or non-functional. The `*-thirdparty.zip` file is for informational purposes only and does not need to be downloaded._
* **GitHub**: https://github.com/fortify-ps/fortify-ssc-bugtracker-register-buglinks
* **Automated builds**: https://github.com/fortify-ps/fortify-ssc-bugtracker-register-buglinks/actions

## Plugin Installation

These sections describe how to install, upgrade and uninstall the plugin.

### Install & Upgrade

* Obtain the plugin binary jar file
	* Either download the binary (see [Related Links](#related-links)) 
	* Or build yourself (see [Developers](#developers))
* If you already have another version of the plugin installed, first uninstall the previously 
 installed version of the plugin by following the steps under [Uninstall](#uninstall) below
* In Fortify Software Security Center:
	* Navigate to Administration->Plugins->Bug Tracking
	* Click the `NEW` button
	* Accept the warning
	* Upload the plugin jar file
	* Enable the plugin by clicking the `ENABLE` button
  
### Uninstall

* In Fortify Software Security Center:
	* Navigate to Administration->Plugins->Bug Tracking
	* Select the parser plugin that you want to uninstall
	* Click the `DISABLE` button
	* Click the `REMOVE` button 


## Usage

For each application version where you want to register bug links, you will need to enable the plugin:

* Navigate to the application version for which the plugin should be enabled
* Clicking the `Profile`
* Navigate to the `Bug Tracker` tab
* Select `Register bug links` from the drop-down list
* Click the `Apply` button

Once the plugin has been enabled:

* The 'Submit Bug' buttons van be used to register bug links
* Once a bug link has been registered, the link can be visited by clicking the 'View Bug' button
* All bug links can be cleared by (temporarily) applying the `No Bug Tracker Integration` option in the `Profile` menu


## Developers

The following sections provide information that may be useful for developers of this utility.

### Gradle Wrapper

It is strongly recommended to build this project using the included Gradle Wrapper
scripts; using other Gradle versions may result in build errors and other issues.

The Gradle build uses various helper scripts from https://github.com/fortify-ps/gradle-helpers;
please refer to the documentation and comments in included scripts for more information. 

### Common Commands

All commands listed below use Linux/bash notation; adjust accordingly if you
are running on a different platform. All commands are to be executed from
the main project directory.

* `./gradlew tasks --all`: List all available tasks
* Build: (plugin binary will be stored in `build/libs`)
	* `./gradlew clean build`: Clean and build the project
	* `./gradlew build`: Build the project without cleaning
	* `./gradlew dist distThirdParty`: Build distribution zip and third-party information bundle
* `./fortify-scan.sh`: Run a Fortify scan; requires Fortify SCA to be installed

### Automated Builds

This project uses GitHub Actions workflows to perform automated builds for both development and production releases. All pushes to the main branch qualify for building a production release. Commits on the main branch should use [Conventional Commit Messages](https://www.conventionalcommits.org/en/v1.0.0/); it is recommended to also use conventional commit messages on any other branches.

User-facing commits (features or fixes) on the main branch will trigger the [release-please-action](https://github.com/google-github-actions/release-please-action) to automatically create a pull request for publishing a release version. This pull request contains an automatically generated CHANGELOG.md together with a version.txt based on the conventional commit messages on the main branch. Merging such a pull request will automatically publish the production binaries and Docker images to the locations described in the [Related Links](#related-links) section.

Every push to a branch in the GitHub repository will also automatically trigger a development release to be built. By default, development releases are only published as build job artifacts. However, if a tag named `dev_<branch-name>` exists, then development releases are also published to the locations described in the [Related Links](#related-links) section. The `dev_<branch-name>` tag will be automatically updated to the commit that triggered the build.


## License
<x-insert text="<!--"/>

See [LICENSE.TXT](LICENSE.TXT)

<x-insert text="-->"/>

<x-include url="file:LICENSE.TXT"/>

