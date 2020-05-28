package com.facomobile.doctors.ui.main;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.facomobile.doctors.data.AppRepository;


public class MainActivityViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private final AppRepository mRepository;

    public MainActivityViewModelFactory(AppRepository repository) {
        this.mRepository = repository;
    }

    @Override
    @NonNull
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        //noinspection unchecked
        return (T) new MainActivityViewModel(mRepository);
    }
}
