/*
 * Copyright (c) 2002-2019, Mairie de Paris
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
package fr.paris.lutece.plugins.participatoryideation.modules.participatorybudget.service.export;

import java.util.Map;

import org.json.JSONObject;

import fr.paris.lutece.plugins.participatorybudget.service.project.ProjectService;
import fr.paris.lutece.plugins.participatoryideation.service.rest.AbstractRestBasedService;
import fr.paris.lutece.portal.service.spring.SpringContextService;
import fr.paris.lutece.portal.service.util.AppLogService;
import fr.paris.lutece.portal.service.util.AppPropertiesService;

/**
 * This class provides 'export project' services from plugin-participatorybudget. It uses the REST API of the plugin.
 */
public class ExportToBudgetService extends AbstractRestBasedService implements IExportToBudgetService
{

    private static final String REST_URL = AppPropertiesService.getProperty( "export.rest.webapp.url" )
            + AppPropertiesService.getProperty( "export.rest.demand.base_url" );

    // *********************************************************************************************
    // * SINGLETON SINGLETON SINGLETON SINGLETON SINGLETON SINGLETON SINGLETON SINGLETON SINGLETON *
    // * SINGLETON SINGLETON SINGLETON SINGLETON SINGLETON SINGLETON SINGLETON SINGLETON SINGLETON *
    // *********************************************************************************************

    private static final String BEAN_EXPORTTOBUDGET_SERVICE = "participatoryideation-participatorybudget.exportToBudgetService";

    private static ExportToBudgetService _singleton;

    public static ExportToBudgetService getInstance( )
    {
        if ( _singleton == null )
        {
            _singleton = SpringContextService.getBean( BEAN_EXPORTTOBUDGET_SERVICE );
        }

        return _singleton;
    }

    // *********************************************************************************************
    // * EXPORT EXPORT EXPORT EXPORT EXPORT EXPORT EXPORT EXPORT EXPORT EXPORT EXPORT EXPORT EXPOR *
    // * EXPORT EXPORT EXPORT EXPORT EXPORT EXPORT EXPORT EXPORT EXPORT EXPORT EXPORT EXPORT EXPOR *
    // *********************************************************************************************

    @Override
    public int exportToParticipatoryBudgetAction( Map<String, String> docFields ) throws Exception
    {
        return ProjectService.getInstance( ).createproject( docFields );
    }

}
