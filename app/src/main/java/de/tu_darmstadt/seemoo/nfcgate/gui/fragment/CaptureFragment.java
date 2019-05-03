package de.tu_darmstadt.seemoo.nfcgate.gui.fragment;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.ImageViewCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import de.tu_darmstadt.seemoo.nfcgate.R;

public class CaptureFragment extends BaseFragment {
    // UI references
    LinearLayout mStartButton, mStopButton;
    TextView mIdleText, mProgressText;
    ImageView mStartIcon, mStopIcon;

    // state
    boolean mCaptureActive = false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_capture, container, false);

        // setup
        mStartButton = v.findViewById(R.id.capture_start);
        mStopButton = v.findViewById(R.id.capture_stop);
        mIdleText = v.findViewById(R.id.capture_idle_text);
        mProgressText = v.findViewById(R.id.capture_progress_text);
        mStartIcon = v.findViewById(R.id.capture_start_icon);
        mStopIcon = v.findViewById(R.id.capture_stop_icon);

        updateState();

        // handlers
        mStartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startCapture();
            }
        });
        mStopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopCapture();
            }
        });

        return v;
    }

    void updateState() {
        mStartIcon.setColorFilter(mCaptureActive ? 0 : 0xffd50000);
        mStartButton.setEnabled(!mCaptureActive);
        mStopIcon.setColorFilter(mCaptureActive ? 0xffd50000 : 0);
        mStopButton.setEnabled(mCaptureActive);
        mIdleText.setVisibility(mCaptureActive ? View.GONE : View.VISIBLE);
        mProgressText.setVisibility(mCaptureActive ? View.VISIBLE : View.GONE);
    }

    void startCapture() {
        mCaptureActive = true;
        updateState();

        getNfc().enableCapture();
    }

    void stopCapture() {
        mCaptureActive = false;
        updateState();

        getNfc().disableCapture();
    }

}