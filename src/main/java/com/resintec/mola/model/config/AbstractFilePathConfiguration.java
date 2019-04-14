package com.resintec.mola.model.config;

import java.io.Serializable;

/**
 * the path configuration for generation
 * @author woodenlock
 *
 */
public abstract class AbstractFilePathConfiguration implements Serializable{
    private static final long serialVersionUID = 1L;

    /** default directory path of the templates **/
    private static final String DEFAULT_TEMPLATE_DIRECTORY =
        AbstractFilePathConfiguration.class.getResource("/templates/generation/").getPath();

    /**
     * main directory of the target project
     */
    private String mainDirectory = "src/main/";

    /**
     * test directory of the target project
     */
    private String testDirectory = "src/test/";

    /**
     * base directory of the target java source file
     */
    private String sourceClass = "java/";

    /**
     * base package of the target java source file
     */
    private String businessPackage = "com/resintec/mola/";

    /**
     * base directory of the target sources file
     */
    private String baseSources = "resources/";
    
    /**
     * a simple method to init assigned member variables
     * @return
     */
    public void build() {
        if (null == mapper) {
            mapper = new SinglePathConfiguration(mainDirectory + baseSources, "mapper/", "Mapper", ".xml",
                DEFAULT_TEMPLATE_DIRECTORY, "mapper.ftl", true);
        }
        if (null == dao) {
            dao = new SinglePathConfiguration(mainDirectory + sourceClass, businessPackage + "dao/", "Dao",
                ".java", DEFAULT_TEMPLATE_DIRECTORY, "dao.ftl", true);
        }
        if (null == service) {
            service = new SinglePathConfiguration(mainDirectory + sourceClass, businessPackage + "service/", "Service",
                ".java", DEFAULT_TEMPLATE_DIRECTORY, "service.ftl", true);
        }
        if (null == serviceImpl) {
            serviceImpl = new SinglePathConfiguration(mainDirectory + sourceClass, businessPackage + "service/impl/",
                "ServiceImpl", ".java", DEFAULT_TEMPLATE_DIRECTORY, "serviceImpl.ftl", true);
        }
        if (null == controller) {
            controller = new SinglePathConfiguration(mainDirectory + sourceClass, businessPackage + "controller/",
                "Controller", ".java", DEFAULT_TEMPLATE_DIRECTORY, "controller.ftl", true);
        }
        if (null == entity) {
            entity = new SinglePathConfiguration(mainDirectory + sourceClass, businessPackage + "model/entity/", "",
                ".java", DEFAULT_TEMPLATE_DIRECTORY, "entity.ftl", true);
        }
        if (null == viewObject) {
            viewObject = new SinglePathConfiguration(mainDirectory + sourceClass, businessPackage + "model/vo/", "VO",
                ".java", DEFAULT_TEMPLATE_DIRECTORY, "viewObject.ftl", true);
        }
        if (null == testService) {
            testService = new SinglePathConfiguration(testDirectory + sourceClass, businessPackage + "service/", "Test",
                ".java", DEFAULT_TEMPLATE_DIRECTORY, "testService.ftl", true);
        }
        // TODO Attention:more file to generate need to be inited here too.
    }

    /**
     * config of the mapper xml file
     */
    private SinglePathConfiguration mapper;

    /**
     * config of the DAO file
     */
    private SinglePathConfiguration dao;

    /**
     * config of the service file
     */
    private SinglePathConfiguration service;

    /**
     * config of the service implement file
     */
    private SinglePathConfiguration serviceImpl;

    /**
     * config of the controller file
     */
    private SinglePathConfiguration controller;

    /**
     * config of the entity file
     */
    private SinglePathConfiguration entity;

    /**
     * config of the view object file
     */
    private SinglePathConfiguration viewObject;

    /**
     * config of the test of the service implement file
     */
    private SinglePathConfiguration testService;

    public String getMainDirectory() {
        return mainDirectory;
    }

    public void setMainDirectory(String mainDirectory) {
        this.mainDirectory = mainDirectory;
    }

    public String getTestDirectory() {
        return testDirectory;
    }

    public void setTestDirectory(String testDirectory) {
        this.testDirectory = testDirectory;
    }

    public String getSourceClass() {
        return sourceClass;
    }

    public void setSourceClass(String sourceClass) {
        this.sourceClass = sourceClass;
    }

    /**
     * @return the businessPackage
     */
    public String getBusinessPackage() {
        return businessPackage;
    }

    /**
     * @param businessPackage
     */
    public void setBusinessPackage(String businessPackage) {
        this.businessPackage = businessPackage;
    }

    public String getBaseSources() {
        return baseSources;
    }

    public void setBaseSources(String baseSources) {
        this.baseSources = baseSources;
    }

    public SinglePathConfiguration getMapper() {
        return mapper;
    }

    public void setMapper(SinglePathConfiguration mapper) {
        this.mapper = mapper;
    }

    public SinglePathConfiguration getDao() {
        return dao;
    }

    public void setDao(SinglePathConfiguration dao) {
        this.dao = dao;
    }

    public SinglePathConfiguration getService() {
        return service;
    }

    public void setService(SinglePathConfiguration service) {
        this.service = service;
    }

    public SinglePathConfiguration getServiceImpl() {
        return serviceImpl;
    }

    public void setServiceImpl(SinglePathConfiguration serviceImpl) {
        this.serviceImpl = serviceImpl;
    }

    public SinglePathConfiguration getController() {
        return controller;
    }

    public void setController(SinglePathConfiguration controller) {
        this.controller = controller;
    }

    public SinglePathConfiguration getEntity() {
        return entity;
    }

    public void setEntity(SinglePathConfiguration entity) {
        this.entity = entity;
    }

    public SinglePathConfiguration getViewObject() {
        return viewObject;
    }

    public void setViewObject(SinglePathConfiguration viewObject) {
        this.viewObject = viewObject;
    }

    public SinglePathConfiguration getTestService() {
        return testService;
    }

    public void setTestService(SinglePathConfiguration testService) {
        this.testService = testService;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("AbstractFilePathConfiguration [mainDirectory=");
        builder.append(mainDirectory);
        builder.append(", testDirectory=");
        builder.append(testDirectory);
        builder.append(", sourceClass=");
        builder.append(sourceClass);
        builder.append(", businessPackage=");
        builder.append(businessPackage);
        builder.append(", baseSources=");
        builder.append(baseSources);
        builder.append(", mapper=");
        builder.append(mapper);
        builder.append(", dao=");
        builder.append(dao);
        builder.append(", service=");
        builder.append(service);
        builder.append(", serviceImpl=");
        builder.append(serviceImpl);
        builder.append(", controller=");
        builder.append(controller);
        builder.append(", entity=");
        builder.append(entity);
        builder.append(", viewObject=");
        builder.append(viewObject);
        builder.append(", testService=");
        builder.append(testService);
        builder.append("]");
        return builder.toString();
    }
}