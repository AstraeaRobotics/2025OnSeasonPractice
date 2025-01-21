// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.TankDriveSub;
import frc.robot.Constants.GrabberConstants;
import frc.robot.Constants.GrabberConstants.PivotStates;
import frc.robot.subsystems.GrabberSubsystem;
import frc.robot.Constants.WinchConstants;
import frc.robot.subsystems.WinchSubsystem;
import frc.robot.Constants.WinchConstants.WinchStates;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class AutoPickUp extends SequentialCommandGroup {
  /** Creates a new AutoPickUp. */
  GrabberSubsystem m_GrabberSubsystem;
  WinchSubsystem m_WinchSubsystem;
  public AutoPickUp() {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
    new ParallelCommandGroup(new SetGrabberPivotState(m_GrabberSubsystem, PivotStates.kFull), new SetWinchState(m_WinchSubsystem, WinchStates.kFull)),
    new WaitCommand(1),
    new ParallelDeadlineGroup(new WaitCommand(2), new MoveIntake(m_GrabberSubsystem, .1)),
    new ParallelCommandGroup(new SetGrabberPivotState(m_GrabberSubsystem, PivotStates.kGround), new SetWinchState(m_WinchSubsystem, WinchStates.kGround)),
    new WaitCommand(1)
    );
  }
}
