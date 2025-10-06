package com.example.workflow.battle;

import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component("prepareToBattle")
public class PrepareToBattleDelegate implements JavaDelegate {

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        Long warriors = (Long) execution.getVariable("warriors");

        if (warriors == null || warriors < 1 || warriors > 100) {
            throw new BpmnError("warriorsError", "Недопустимое кол-во воинов: " + warriors);
        }

        Random random = new Random();
        int enemyWarriors = random.nextInt(100);
        execution.setVariable("enemyWarriors", enemyWarriors);

        boolean isWin = warriors > enemyWarriors;
        execution.setVariable("isWin", isWin);

        String status = isWin ? "Victory" : "Fail";
        execution.setVariable("battleStatus", status);

    }
}