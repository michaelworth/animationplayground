package com.slack.animationplayground.collapsingheader;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.transition.TransitionManager;
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
    TransitionManager.beginDelayedTransition(rootView);
    toggleVisibility(headerTitleCollapsed);
    toggleVisibility(headerTitleExpanded);
    toggleVisibility(headerTextExpanded);
  }

  private void toggleVisibility(@NonNull View view) {
    view.setVisibility(view.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
  }

}
