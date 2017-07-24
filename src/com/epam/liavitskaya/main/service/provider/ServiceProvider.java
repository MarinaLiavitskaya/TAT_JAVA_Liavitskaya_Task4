package com.epam.liavitskaya.main.service.provider;

import com.epam.liavitskaya.main.service.ClientService;
import com.epam.liavitskaya.main.service.LibraryService;
import com.epam.liavitskaya.main.service.impl.ClientServiceImpl;
import com.epam.liavitskaya.main.service.impl.LibraryServiceImpl;

public final class ServiceProvider {

	private static final ServiceProvider instance = new ServiceProvider();

	private final LibraryService libraryServiceImpl = new LibraryServiceImpl();
	private final ClientService clientServiceImpl = new ClientServiceImpl();

	public static ServiceProvider getInstance() {
		return instance;
	}

	public ClientService getClientServiceImpl() {
		return clientServiceImpl;
	}

	public LibraryService getLibraryServiceImpl() {
		return libraryServiceImpl;
	}
}
