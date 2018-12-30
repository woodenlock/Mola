<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${all.dao.targetPackagePath?replace('/','.')}${table.entityName? cap_first}${all.dao.targetSuffix}">
<resultMap id="BaseResultMap" type="${all.entity.targetPackagePath?replace('/','.')}${table.entityName? cap_first}${all.entity.targetSuffix}">
<#list table.columns as column>
	<<#if column.isPrimary??>id<#else>result</#if> column="${column.name}" jdbcType="${column.typeName}" property="${column.javaName}" />
</#list>
</resultMap>
<sql id="Base_Column_List">
<#list table.columns as column>${column.name}<#if column_has_next>,</#if></#list>
</sql>
<select id="selectByPrimaryKey" parameterType="${table.primaryColumn.javaTypeFullName}" resultMap="BaseResultMap">
    select <include refid="Base_Column_List" /> from ${table.name}
    where ${table.primaryColumn.name} = ${r"#{"}${table.primaryColumn.javaName},jdbcType=${table.primaryColumn.typeName}}
</select>
<delete id="deleteByPrimaryKey" parameterType="${table.primaryColumn.javaTypeFullName}">
    delete from ${table.name}
    where ${table.primaryColumn.name} = ${r"#{"}${table.primaryColumn.javaName},jdbcType=${table.primaryColumn.typeName}}
</delete>
<insert id="insert" parameterType="${all.entity.targetPackagePath?replace('/','.')}${table.entityName? cap_first}${all.entity.targetSuffix}">
    <selectKey keyProperty="${table.primaryColumn.name}" order="AFTER" resultType="${table.primaryColumn.javaTypeFullName}">
      SELECT LAST_INSERT_ID()
    </selectKey>
    insert into ${table.name} (<#list table.columns as column><#if !column.isPrimary??>${column.name}<#if column_has_next>,</#if></#if></#list>)
    values (<#list table.columns as column><#if !column.isPrimary??>${r"#{"}${column.javaName},jdbcType=${column.typeName}}<#if column_has_next>,</#if></#if></#list>)
</insert>
<insert id="insertSelective" parameterType="${all.entity.targetPackagePath?replace('/','.')}${table.entityName? cap_first}${all.entity.targetSuffix}">
    <selectKey keyProperty="id" order="AFTER" resultType="${table.primaryColumn.javaTypeFullName}">
      SELECT LAST_INSERT_ID()
    </selectKey>
    insert into ${table.name}
    <trim prefix="(" suffix=")" suffixOverrides=",">
      <#list table.columns as column>
      <#if !column.isPrimary??>
      <if test="${column.javaName} != null">
        ${column.name},
      </if>
      </#if>
      </#list>
    </trim>
    <trim prefix="values (" suffix=")" suffixOverrides=",">
      <#list table.columns as column>
      <#if !column.isPrimary??>
      <if test="${column.javaName} != null">
        ${r"#{"}${column.javaName},jdbcType=${column.typeName}},
      </if>
      </#if>
      </#list>
    </trim>
</insert>
<update id="updateByPrimaryKeySelective" parameterType="${all.entity.targetPackagePath?replace('/','.')}${table.entityName? cap_first}${all.entity.targetSuffix}">
    update ${table.name}
    <set>
      <#list table.columns as column>
      <if test="${column.javaName} != null">
        ${column.name} = ${r"#{"}${column.javaName},jdbcType=${column.typeName}},
      </if>
      </#list>
    </set>
    where ${table.primaryColumn.name} = ${r"#{"}${table.primaryColumn.javaName},jdbcType=${table.primaryColumn.typeName}}
</update>
<update id="updateByPrimaryKey" parameterType="${all.entity.targetPackagePath?replace('/','.')}${table.entityName? cap_first}${all.entity.targetSuffix}">
    update ${table.name}
    set 
      <#list table.columns as column>
      ${column.name} = ${r"#{"}${column.javaName},jdbcType=${column.typeName}}<#if column_has_next>,</#if>
      </#list>
    where ${table.primaryColumn.name} = ${r"#{"}${table.primaryColumn.javaName},jdbcType=${table.primaryColumn.typeName}}
</update>
<select id="selectSelective" parameterType="${all.entity.targetPackagePath?replace('/','.')}${table.entityName? cap_first}${all.entity.targetSuffix}" resultMap="BaseResultMap">
    select
    <include refid="Base_Column_List" />
    from ${table.name}
    <where>
      <#list table.columns as column>
      <if test="${column.javaName} != null">
        and ${column.name} = ${r"#{"}${column.javaName},jdbcType=VARCHAR}
      </if>
      </#list>
    </where>
</select>
<select id="selectByPrimarys" resultMap="BaseResultMap">
    select
    <include refid="Base_Column_List" />
    from ${table.name}
    <where>
    	<if test="${table.primaryColumn.name}s != null">
    		 AND ${table.primaryColumn.name} in 
    		<foreach collection="${table.primaryColumn.name}s" item="id" open="(" separator="," close=")">
        		${r"#{"}${table.primaryColumn.javaName},jdbcType=${table.primaryColumn.typeName}}
    		</foreach>
    	</if>
    </where>
</select>
</mapper>