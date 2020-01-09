/*
 * Copyright (c) 2002-2020, Mairie de Paris
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *  1. Redistributions of source code must retain the above copyright notice
 *     and the following disclaimer.
 *
 *  2. Redistributions in binary form must reproduce the above copyright notice
 *     and the following disclaimer in the documentation and/or other materials
 *     provided with the distribution.
 *
 *  3. Neither the name of 'Mairie de Paris' nor 'Lutece' nor the names of its
 *     contributors may be used to endorse or promote products derived from
 *     this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDERS OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 *
 * License 1.0
 */
package fr.paris.lutece.plugins.participatoryideation.modules.participatorybudget.service.myinfos;

import fr.paris.lutece.plugins.participatoryideation.service.myinfos.IMyInfosService;
import fr.paris.lutece.plugins.participatoryideation.service.rest.AbstractRestBasedService;
import fr.paris.lutece.portal.service.util.AppPropertiesService;

/**
 * This class provides 'myinfos' services from plugin-participatorybudget. It uses the REST API of the plugin.
 */
public class MyInfosFromParticipatoryBudgetService extends AbstractRestBasedService implements IMyInfosService
{

    private final static String REST_URL = AppPropertiesService.getProperty( "participatoryideation.myinfos.rest.webapp.url" )
            + AppPropertiesService.getProperty( "participatoryideation.myinfos.rest.demand.base_url" );

    // *********************************************************************************************
    // * ISVALID ISVALID ISVALID ISVALID ISVALID ISVALID ISVALID ISVALID ISVALID ISVALID ISVALID I *
    // * ISVALID ISVALID ISVALID ISVALID ISVALID ISVALID ISVALID ISVALID ISVALID ISVALID ISVALID I *
    // *********************************************************************************************

    @Override
    public boolean isUserValid( String userId )
    {
        return parseBoolean( REST_URL + userId + "/are-myinfos-valid" );
    }

    // *********************************************************************************************
    // * FILL FILL FILL FILL FILL FILL FILL FILL FILL FILL FILL FILL FILL FILL FILL FILL FILL FILL *
    // * FILL FILL FILL FILL FILL FILL FILL FILL FILL FILL FILL FILL FILL FILL FILL FILL FILL FILL *
    // *********************************************************************************************

    @Override
    public String getUrlMyInfosFillAction( )
    {
        return parseString( REST_URL + "url-myinfos-fill-action" );
    }

}
