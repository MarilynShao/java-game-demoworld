#!/bin/bash

if [ -f submission.zip ]; then
  old=$(date +%s)
  echo "Backup up old submission as submission-${old}"
  echo
  mv submission.zip submission-"${old}".zip
fi

touch refs.md

zip submission.zip src/demoworld/model/adjustments/Adjustment.java
zip submission.zip src/demoworld/model/adjustments/MaxHpAdjustment.java
zip submission.zip src/demoworld/model/adjustments/MaxXpAdjustment.java
zip submission.zip src/demoworld/model/adjustments/StatAdjustment.java
zip submission.zip src/demoworld/model/Adjuster.java
zip submission.zip src/demoworld/model/Character.java
zip submission.zip src/demoworld/model/Experience.java
zip submission.zip src/demoworld/model/Feature.java
zip submission.zip src/demoworld/model/FeatureManager.java
zip submission.zip src/demoworld/model/Hitpoints.java
zip submission.zip src/demoworld/model/PrimaryStat.java
zip submission.zip src/demoworld/model/Requirement.java
zip submission.zip src/demoworld/model/RequirementManager.java
zip submission.zip src/demoworld/model/Specialty.java
zip submission.zip src/demoworld/model/SpecialtyManager.java
zip submission.zip src/demoworld/model/Stat.java
zip submission.zip src/demoworld/model/StatManager.java
zip submission.zip src/demoworld/model/Value.java

#zip -r submission.zip test/*
zip submission.zip refs.md
