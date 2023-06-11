package com.example.myschoolnotes.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.myschoolnotes.R;

public class PomodoroActivity extends AppCompatActivity {
    private TextView timerTextView;
    private Button startButton;
    private Button resetButton;
    private CountDownTimer countDownTimer;
    private boolean isTimerRunning;
    private long workDuration = 25 * 60 * 1000;
    private long breakDuration = 5 * 60 * 1000;

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pomodoro);
        timerTextView = findViewById(R.id.timerTextView);
        startButton = findViewById(R.id.startButton);
        resetButton = findViewById(R.id.resetButton);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setMax((int) workDuration / 1000);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isTimerRunning) {
                    pauseTimer();
                } else {
                    startTimer();
                }
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetTimer();
            }
        });
    }

    private void startTimer() {
        countDownTimer = new CountDownTimer(workDuration, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                progressBar.setProgress((int) workDuration / 1000);
                updateTimer(millisUntilFinished);
            }

            @Override
            public void onFinish() {
                // Se ha completado el tiempo de trabajo, inicia el tiempo de descanso
                timerTextView.setText("00:00");
                startBreakTimer();
            }
        }.start();

        startButton.setText("Pause");
        resetButton.setEnabled(true);
        isTimerRunning = true;
    }

    private void pauseTimer() {
        countDownTimer.cancel();
        startButton.setText("Start");
        isTimerRunning = false;
    }

    private void resetTimer() {
        if (isTimerRunning) {
            countDownTimer.cancel();
            isTimerRunning = false;
        }

        timerTextView.setText("00:00");
        startButton.setText("Start");
        resetButton.setEnabled(false);
    }

    private void startBreakTimer() {
        countDownTimer = new CountDownTimer(breakDuration, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                updateTimer(millisUntilFinished);
            }

            @Override
            public void onFinish() {
                // Se ha completado el tiempo de descanso, reinicia el tiempo de trabajo
                timerTextView.setText("00:00");
                startTimer();
            }
        }.start();
    }

    private void updateTimer(long millisUntilFinished) {
        int minutes = (int) (millisUntilFinished / 1000) / 60;
        int seconds = (int) (millisUntilFinished / 1000) % 60;
        String timeLeftFormatted = String.format("%02d:%02d", minutes, seconds);
        int totalSeconds = (int) (workDuration / 1000);
        int progress = (int) ((workDuration - millisUntilFinished) / 1000);
        progressBar.setProgress(progress);
        timerTextView.setText(timeLeftFormatted);
    }
}