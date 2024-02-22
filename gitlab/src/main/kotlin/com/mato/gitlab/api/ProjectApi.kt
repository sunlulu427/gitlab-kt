package com.mato.gitlab.api

import com.mato.gitlab.response.Project
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @author sunlulu.tomato
 * @date 02/19/2024
 * @sample com.mato.gitlab.api.ProjectApiTest
 */
interface ProjectApi {

    @GET("projects")
    suspend fun getAllProjects(
        @Query("archived") archived: Boolean? = null,
        @Query("id_after") idAfter: Int? = null,
        @Query("id_before") idBefore: Int? = null,
        @Query("imported") imported: Boolean? = null,
        @Query("include_hidden") includeHidden: Boolean? = null,
        @Query("include_pending_delete") includePendingDelete: Boolean? = null,
        @Query("last_activity_after") lastActivityAfter: String? = null,
        @Query("last_activity_before") lastActivityBefore: String? = null,
        @Query("membership") membership: Boolean? = null,
        @Query("min_access_level") minAccessLevel: Int? = null,
        @Query("order_by") orderBy: String? = null,
        @Query("owned") owned: Boolean? = null,
        @Query("repository_checksum_failed") repositoryChecksumFailed: Boolean? = null,
        @Query("repository_storage") repositoryStorage: String? = null,
        @Query("search_namespaces") searchNameSpaces: Boolean? = null,
        @Query("search") search: String? = null,
        @Query("simple") simple: Boolean? = null,
        @Query("sort") sort: String? = null,
        @Query("starred") starred: Boolean? = null,
        @Query("statistics") statistics: Boolean? = null,
        @Query("topic_id") topicId: Int? = null,
        @Query("topic") topic: String? = null,
        @Query("updated_after") updateAfter: String? = null,
        @Query("updated_before") updateBefore: String? = null,
        @Query("visibility") visibility: String? = null,
        @Query("wiki_checksum_failed") wikiChecksumFailed: Boolean? = null,
        @Query("with_custom_attributes") withCustomAttributes: Boolean? = null,
        @Query("with_issues_enabled") withIssueEnabled: Boolean? = null,
        @Query("with_merge_requests_enabled") withMergeRequestEnabled: Boolean? = null,
        @Query("with_programming_language") withProgrammingLanguage: String? = null
    ): Result<List<Project>>
}