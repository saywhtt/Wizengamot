package edu.born.flicility.di.modules

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Handler
import dagger.Module
import dagger.Provides
import edu.born.flicility.PhotoDownloader
import edu.born.flicility.adapters.PhotoAdapter
import edu.born.flicility.di.scopes.PhotoScope
import edu.born.flicility.network.PhotoService
import edu.born.flicility.presenters.PhotoListPresenter
import edu.born.flicility.presenters.PhotoListPresenterImpl

@Module
class PhotoModule {

    @Provides
    @PhotoScope
    fun providePhotoGalleryPresenter(photoService: PhotoService): PhotoListPresenter {
        return PhotoListPresenterImpl(photoService)
    }

    @Provides
    @PhotoScope
    fun providePhotoDownloader(handler: Handler, context: Context): PhotoDownloader {
        val photoDownloader = PhotoDownloader(handler)

        photoDownloader.setPhotoDownloadListener {
            holder: PhotoAdapter.PhotoHolder, bitmap: Bitmap? -> holder.bind(BitmapDrawable(context.resources, bitmap))
        }

        return photoDownloader
    }

    @Provides
    @PhotoScope
    fun providePhotoAdapter(photoDownloader: PhotoDownloader): PhotoAdapter {
        return PhotoAdapter(photoDownloader)
    }

}