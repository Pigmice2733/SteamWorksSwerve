# SteamWorksSwerve
The repository for the swerve drive code for the 2017 Steamworks competition. It is intended to be easy to repurpose and use for later competitions or possibly as a library in future revisions. Currently it supports both tank drive, and swerve drive although later versions may choose to remove the support for tank drive.

## Test code

There are two categories of tests for this project.

### JUnit Tests
The first (and currently less used) are the common java JUnit tests. In order to run the tests in eclipse do the following:

1. Have a copy of SteamWorksSwerve open
2. Go to Window -> Show View -> Other -> Ant -> Ant. Click "OK".
3. An "Ant" window should have opened. If an "FRC Deployment" option is not visible then:
    1. In that window click the "Add Buildfiles" button (it looks like an black ant with a green plus)
    2. Click the down arrow next to "SteamWorksSwerve"
    3. Select "build.xml" and then "OK"
4. Under "FRC Deployment" double click "test"
5. If you see a huge list of build.xml (some version), don't panic just click "OK" with the first one selected.
6. If any errors appear in the console deal with them :smiling_imp:. The errors (and successes) can be found under the folder "unitTests" in the projects main directory.

### FRC Driver Station Tests
The secondary category of tests are activatable in the FRC Driver Station. **Hopefully** the developer of the test put a simple to use and easy option to activate it. Find it, run it. See the output, do what you will...
