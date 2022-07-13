package com.zrcoding.shared.data.datastore

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import com.google.protobuf.InvalidProtocolBufferException
import java.io.InputStream
import java.io.OutputStream
import javax.inject.Inject

class UserSettingSerializer @Inject constructor() : Serializer<UserSettingsPreferences> {
    override val defaultValue: UserSettingsPreferences
        get() = UserSettingsPreferences.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): UserSettingsPreferences {
        try {
            return UserSettingsPreferences.parseFrom(input)
        } catch (exception: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto.", exception)
        }
    }

    override suspend fun writeTo(t: UserSettingsPreferences, output: OutputStream) {
        t.writeTo(output)
    }

}