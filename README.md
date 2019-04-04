# validation
参数校验框架,使用注解+xml配置校验规则实现controller通用参数校验

配置文件在(application-validation.xm)放在项目resource根目录下

Controller使用示例:

  @PostMapping("/v1/portal/menuWeb")
	@Validation(id = "MenuWebParam",group = "demo")
	public ResultVo menuWeb(@RequestBody MenuWebParam menuWebParam,
			HttpServletRequest request) {
		//业务逻辑..
		return ResultVOUtil.success(null, "sss");
	}
