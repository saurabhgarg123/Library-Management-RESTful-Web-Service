package edu.sjsu.cmpe.library;

	import org.codehaus.jackson.map.Module;

	import com.yammer.dropwizard.Service;
	import com.yammer.dropwizard.config.Environment;
	import com.yammer.dropwizard.json.Json;

	import edu.sjsu.cmpe.library.api.resources.BookResource;
	
	import edu.sjsu.cmpe.library.api.resources.RootResource;
	import edu.sjsu.cmpe.library.config.CustomJson;
	import edu.sjsu.cmpe.library.config.LibraryServiceConfiguration;


public class LibraryService extends Service<LibraryServiceConfiguration> {

		public static void main(String[] args) throws Exception {
			
			new LibraryService().run(args);
		}
		
		private LibraryService()
		{
			super("library");
		}

		@Override
		protected void initialize(LibraryServiceConfiguration configuration, Environment environment) throws Exception
		{
			environment.addResource(RootResource.class);
			environment.addResource(BookResource.class);
			//environment.addResource(ReviewResource.class);
			
		}
		
		@Override
		public Json getJson()
		{
			//System.out.println("Test");
			final CustomJson json=new CustomJson();
			for(Module module : getJacksonModules())
			{
				json.registerModule(module);
			
			}
			return json;
		}
}
