/*
 * Copyright 2016 Nikolay Kucheriaviy
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.ne1c.rainbowmvp.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import com.ne1c.rainbowmvp.PresenterLoader;

public abstract class BaseFragment<P extends BasePresenter> extends Fragment implements LoaderManager.LoaderCallbacks<P> {
    protected final int LOADER_ID = 593;

    private P mPresenter;

    private boolean mPresenterDelivered;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getLoaderManager().initLoader(LOADER_ID, null, this);
    }

    @Override
    public Loader<P> onCreateLoader(int id, Bundle args) {
        return new PresenterLoader<>(getActivity(), getPresenterTag());
    }

    @Override
    public void onLoadFinished(Loader<P> loader, P presenter) {
        if (!mPresenterDelivered) {
            mPresenter = presenter;
            mPresenterDelivered = true;
        }
    }

    @Override
    public void onLoaderReset(Loader<P> loader) {
        mPresenter = null;
    }

    protected P getPresenter() {
        return mPresenter;
    }

    protected abstract String getPresenterTag();
}
