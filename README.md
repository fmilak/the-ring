# Patterns
- Singleton -> Autowired anotation for injecting services and repositories
- Builder -> Used when building User class. Example in UserService.registerUser
- Factory -> Used when creating User class for initializing fields based on role. Implemented through UserFactory.
- Prototype -> Used for creating copy of image that is grayscaled or blurred and showing it on screen. Used in PostService.getAllPosts and implemented in Post class.
- Strategy -> Used for limiting the upload of images for specific user types. Used in PostService and implemented with UploadLimitService interface with UserUploadLimitService and AdminUploadLimitService classes that implement it.
- Facade -> Used through services that contain repositories which both hide logic for doing something and you can just call methods without needing to know what happens behind.