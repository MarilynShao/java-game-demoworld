@echo OFF
setlocal enabledelayedexpansion

:: Backup existing submission
if not exist submission.zip goto NO_SUBMISSION_ZIP
echo - Backing up existing submission.zip

:: Get the current date and time in a consistent format
for /f "tokens=2 delims==" %%A in ('"wmic os get localdatetime /value"') do set datetime=%%A

:: Extract date and time components
set yy=!datetime:~0,4!
set mm=!datetime:~4,2!
set dd=!datetime:~6,2!
set hh=!datetime:~8,2!
set min=!datetime:~10,2!
set sec=!datetime:~12,2!

:: Format the timestamp
set timestamp=!yy!!mm!!dd!!hh!!min!!sec!

:: Rename the file
ren submission.zip submission-!timestamp!.zip

:NO_SUBMISSION_ZIP

:: See if there is an existing zip on this system
echo - Checking for an installed zip program
set zipath=
"%zipath%zip" >nul 2>nul
if %ERRORLEVEL%==0 echo   Yes, found one && goto COMMON
echo   No, none found.

:: See if we've been here before and already install gnu zip
echo - Checking if we have installed one before
set zipath=%ProgramFiles(x86)%\GnuWin32\bin\
"%zipath%zip" >nul 2>nul
if %ERRORLEVEL%==0 echo   Yes, we have && goto COMMON

echo   No, we haven't. Let's install one.
echo.
echo You be be asked to allow "download.exe" to run.
echo Please click on YES when it asks for permission.
echo.
pause

winget install gnuwin32.zip

:COMMON
"%zipath%zip" submission.zip src/demoworld/model/adjustments/Adjustment.java
"%zipath%zip" submission.zip src/demoworld/model/adjustments/MaxHpAdjustment.java
"%zipath%zip" submission.zip src/demoworld/model/adjustments/MaxXpAdjustment.java
"%zipath%zip" submission.zip src/demoworld/model/adjustments/StatAdjustment.java
"%zipath%zip" submission.zip src/demoworld/model/Adjuster.java
"%zipath%zip" submission.zip src/demoworld/model/Character.java
"%zipath%zip" submission.zip src/demoworld/model/Experience.java
"%zipath%zip" submission.zip src/demoworld/model/Feature.java
"%zipath%zip" submission.zip src/demoworld/model/FeatureManager.java
"%zipath%zip" submission.zip src/demoworld/model/Hitpoints.java
"%zipath%zip" submission.zip src/demoworld/model/PrimaryStat.java
"%zipath%zip" submission.zip src/demoworld/model/Requirement.java
"%zipath%zip" submission.zip src/demoworld/model/RequirementManager.java
"%zipath%zip" submission.zip src/demoworld/model/Specialty.java
"%zipath%zip" submission.zip src/demoworld/model/SpecialtyManager.java
"%zipath%zip" submission.zip src/demoworld/model/Stat.java
"%zipath%zip" submission.zip src/demoworld/model/StatManager.java
"%zipath%zip" submission.zip src/demoworld/model/Value.java

"%zipath%zip" submission.zip refs.md

:DONE
endlocal
