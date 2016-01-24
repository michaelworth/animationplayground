package com.slack.animationplayground.collapsingheader;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewTreeObserver;

import com.slack.animationplayground.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class CollapsingHeaderActivity extends AppCompatActivity {

    private static final String KEY_IS_COLLAPSED = "key_is_collapsed";

    @Bind(R.id.header_container)
    View headerContainer;
    @Bind(R.id.header_view_collapsed)
    View headerViewCollapsed;
    @Bind(R.id.header_view_expanded)
    View headerViewExpanded;

    private boolean isCollapsed = true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collapsing_header);
        ButterKnife.bind(this);

        if (savedInstanceState != null) {
            isCollapsed = savedInstanceState.getBoolean(KEY_IS_COLLAPSED, true);
        }

        // Retain state post rotation
        if (!isCollapsed) {
            headerViewExpanded.setVisibility(View.VISIBLE);
            headerViewExpanded.setAlpha(1);

            headerViewCollapsed.setVisibility(View.INVISIBLE);
            headerViewCollapsed.setAlpha(0);
        }

        headerContainer.getViewTreeObserver()
                .addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                    @Override
                    public boolean onPreDraw() {
                        headerContainer.setBottom(isCollapsed ?
                                headerViewCollapsed.getBottom() :
                                headerViewExpanded.getBottom());
                        headerContainer.getViewTreeObserver()
                                .removeOnPreDrawListener(this);
                        return true;
                    }
                });

        headerContainer.setOnClickListener(new HeaderClickListener());
    }

    class HeaderClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            if (isCollapsed) {
                ObjectAnimator bottomAnimator = ObjectAnimator.ofInt(
                        headerContainer,
                        "bottom",
                        headerViewCollapsed.getBottom(),
                        headerViewExpanded.getBottom());
                bottomAnimator.setInterpolator(new FastOutSlowInInterpolator());

                headerViewExpanded.setVisibility(View.VISIBLE);
                headerViewExpanded.animate()
                        .alpha(1)
                        .setInterpolator(new FastOutSlowInInterpolator())
                        .setListener(null);

                headerViewCollapsed.animate()
                        .alpha(0)
                        .setInterpolator(new FastOutSlowInInterpolator())
                        .setListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                headerViewCollapsed.setVisibility(View.INVISIBLE);
                            }
                        });
            } else {
                ObjectAnimator bottomAnimator = ObjectAnimator.ofInt(
                        headerContainer,
                        "bottom",
                        headerViewExpanded.getBottom(),
                        headerViewCollapsed.getBottom());
                bottomAnimator.setInterpolator(new FastOutSlowInInterpolator());

                headerViewCollapsed.setVisibility(View.VISIBLE);
                headerViewCollapsed.animate()
                        .alpha(1)
                        .setInterpolator(new FastOutSlowInInterpolator())
                        .setListener(null);

                headerViewExpanded.animate()
                        .alpha(0)
                        .setInterpolator(new FastOutSlowInInterpolator())
                        .setListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                headerViewExpanded.setVisibility(View.INVISIBLE);
                            }
                        });
            }

            isCollapsed = !isCollapsed;
        }
    }

}
