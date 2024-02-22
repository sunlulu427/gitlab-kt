package com.mato.gitlab.api

import com.mato.gitlab.request.FileOperatorRequest
import com.mato.gitlab.response.FileOperatorResponse
import com.mato.gitlab.response.FileBlameInfo
import com.mato.gitlab.response.RepositoryFile
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * @author sunlulu.tomato
 * @date 02/22/2024
 */
interface RepositoryFilesApi {

    @GET("projects/{id}/repository/files/{file_path}")
    suspend fun getFile(
        @Path("id") id: String,
        @Path("file_path") filePath: String,
        @Query("ref") ref: String
    ): Result<RepositoryFile>

    @GET("projects/{id}/repository/files/{file_path}/blame")
    suspend fun getFileBlame(
        @Path("id") id: String,
        @Path("file_path") filePath: String,
        @Query("ref") ref: String,
        @Query("range[end]") rangeEnd: Int,
        @Query("range[start]") rangeStart: Int,
        @Query("range") range: String
    ): Result<FileBlameInfo>

    @GET("projects/{id}/repository/files/{file_path}/raw")
    suspend fun getRawFile(
        @Path("id") id: String,
        @Path("file_path") filePath: String,
        @Query("ref") ref: String? = null,
        @Query("lfs") lfs: Boolean? = null
    ): Result<RepositoryFile>

    @POST("projects/{id}/repository/files/{file_path}")
    fun createNewFile(
        @Path("id") id: String,
        @Path("file_path") filePath: String,
        @Body createFileRequest: FileOperatorRequest
    ): Result<FileOperatorResponse>

    @PUT("projects/{id}/repository/files/{file_path}")
    suspend fun updateExistingFile(
        @Path("id") id: String,
        @Path("file_path") filePath: String,
        @Body updateFileRequest: FileOperatorRequest
    ): Result<FileOperatorResponse>

    @DELETE("projects/{id}/repository/files/{file_path}")
    suspend fun deleteFile(
        @Path("id") id: String,
        @Path("file_path") filePath: String,
        @Body fileOperatorRequest: FileOperatorRequest
    ): Result<Unit>
}