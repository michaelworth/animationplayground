package com.slack.animationplayground.collapsingheader;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.transition.ChangeBounds;
import android.support.transition.Fade;
import android.support.transition.TransitionManager;
import android.support.transition.TransitionSet;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;

import com.slack.animationplayground.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CollapsingHeaderActivity extends AppCompatActivity {

  @Bind(R.id.root_view)
  ViewGroup rootView;
  @Bind(R.id.header_container)
  ViewGroup headerContainer;
  @Bind(R.id.bottom_view)
  View bottomView;
  @Bind(R.id.header_title_collapsed)
  View headerTitleCollapsed;
  @Bind(R.id.header_title_expanded)
  View headerTitleExpanded;
  @Bind(R.id.header_text_expanded)
  View headerTextExpanded;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_collapsing_header);
    ButterKnife.bind(this);
  }

  @OnClick(R.id.header_container)
  void onClickHeader() {
    /**
     * By doing a transition set we are able to tweak delays, ordering, interpolators. This gives
     * a more smooth feel than the default auto-transition which does a fade out, change bounds, and
     * fade in for the views that changed in sequence.
     *
     * Comment out the TransitionSet code and remove that argument from the delayed transition method
     * to compare the custom to the default transition.
     *
     * Note: This could be all done in XML instead to keep the java source tidy.
     */
    TransitionSet transitionSet = new TransitionSet();

    if (headerTitleCollapsed.getVisibility() == View.VISIBLE) {
      transitionSet.addTransition(
          new Fade(Fade.OUT)
              .addTarget(headerTitleCollapsed));

      transitionSet.addTransition(
          new ChangeBounds()
              .addTarget(headerContainer)
              .addTarget(bottomView));

      transitionSet.addTransition(
          new Fade(Fade.IN)
              .setStartDelay(300)
              .addTarget(headerTitleExpanded)
              .addTarget(headerTextExpanded));
    } else {
      transitionSet.addTransition(
          new Fade(Fade.OUT)
              .addTarget(headerTitleExpanded)
              .addTarget(headerTextExpanded));

      transitionSet.addTransition(
          new ChangeBounds()
              .setStartDelay(150)
              .addTarget(headerContainer)
              .addTarget(bottomView));

      transitionSet.addTransition(
          new Fade(Fade.IN)
              .setStartDelay(300)
              .addTarget(headerTitleCollapsed));
    }

    transitionSet.setOrdering(TransitionSet.ORDERING_TOGETHER)
        .setInterpolator(new FastOutSlowInInterpolator());

    TransitionManager.beginDelayedTransition(rootView, transitionSet);
    toggleVisibility(headerTitleCollapsed);
    toggleVisibility(headerTitleExpanded);
    toggleVisibility(headerTextExpanded);
  }

  private void toggleVisibility(@NonNull View view) {
    view.setVisibility(view.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
  }

}
