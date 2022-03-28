#### TCP Socket server integration

This is an example TCP socket client used to log data from the Rio's sensors to the console, the corresponding Java code is in [Robot.java](../src/main/java/frc/robot/Robot.java) (lines ~170-200).

##### why?

Having a flexible method of communication allows any of us to write programming language agnostic displays or controls for the robot. I plan on having this show a top down view of the field, the robots orientation relative to the goal, and some other quality of life things. Potentially a "aimbot" system. Some of these things are only avaible, or far easier, to calculate from/on the driver station.
