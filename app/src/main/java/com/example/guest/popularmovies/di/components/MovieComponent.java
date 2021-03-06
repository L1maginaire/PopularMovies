package com.example.guest.popularmovies.di.components;

/**
 * Created by l1maginaire on 2/20/18.
 */

import com.example.guest.popularmovies.di.modules.MovieModule;
import com.example.guest.popularmovies.di.scope.PerFragment;
import com.example.guest.popularmovies.ui.MainFragment;

import dagger.Component;

@PerFragment
@Component(modules = MovieModule.class, dependencies = ApplicationComponent.class)
public interface MovieComponent {
    void inject(MainFragment fragment);
}