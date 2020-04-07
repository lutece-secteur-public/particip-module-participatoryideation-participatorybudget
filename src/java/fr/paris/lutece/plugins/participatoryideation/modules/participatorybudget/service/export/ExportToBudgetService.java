/*
 * Copyright (c) 2002-2020, City of Paris
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

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import fr.paris.lutece.plugins.participatorybudget.service.project.ProjectService;
import fr.paris.lutece.plugins.participatorybudget.util.ParticipatoryBudgetConstants;
import fr.paris.lutece.plugins.participatoryideation.business.proposal.Proposal;
import fr.paris.lutece.plugins.participatoryideation.service.rest.AbstractRestBasedService;
import fr.paris.lutece.portal.service.spring.SpringContextService;

/**
 * This class provides 'export project' services from plugin-participatorybudget. Should uses a REST API of the plugin.
 */
public class ExportToBudgetService extends AbstractRestBasedService implements IExportToBudgetService
{

    // TODO : Put this portlet id in property file
    public static final int DOCUMENT_PROJECT_PUBLISH_PORTLET_ID = 158;

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
    public int exportToParticipatoryBudgetAction( Proposal proposal ) throws Exception
    {
        String title = proposal.getTitre( );
        String summary = proposal.getTitre( );
        Timestamp valid = new Timestamp( new java.util.Date( ).getTime( ) );

        // Populate project attributes map
        Map<String, String> docFields = new HashMap<>( );

        docFields.put( ParticipatoryBudgetConstants.DOCUMENT_ATTRIBUTE_ADDRESS, proposal.getAdress( ) );
        docFields.put( ParticipatoryBudgetConstants.DOCUMENT_ATTRIBUTE_ADDRESS_GEOLOC, proposal.getGeoJson( ) );
        docFields.put( ParticipatoryBudgetConstants.DOCUMENT_ATTRIBUTE_CAMPAIGN, proposal.getCodeCampaign( ) );
        docFields.put( ParticipatoryBudgetConstants.DOCUMENT_ATTRIBUTE_DESCRIPTION, proposal.getDescription( ) );

        if ( Proposal.LOCATION_TYPE_PARIS.equals( proposal.getLocationType( ) ) )
        {
            docFields.put( ParticipatoryBudgetConstants.DOCUMENT_ATTRIBUTE_LOCATION, Proposal.LOCATION_TYPE_PARIS );
        }
        else
        {
            docFields.put( ParticipatoryBudgetConstants.DOCUMENT_ATTRIBUTE_LOCATION, proposal.getLocationArdt( ) );
        }

        docFields.put( ParticipatoryBudgetConstants.DOCUMENT_ATTRIBUTE_PROPOSAL_ID, Integer.toString( proposal.getId( ) ) );
        docFields.put( ParticipatoryBudgetConstants.DOCUMENT_ATTRIBUTE_PROPOSAL_NICKNAMES, proposal.getDepositary( ) );
        docFields.put( ParticipatoryBudgetConstants.DOCUMENT_ATTRIBUTE_PROPOSAL_SUBTITLE, proposal.getTitre( ) );
        docFields.put( ParticipatoryBudgetConstants.DOCUMENT_ATTRIBUTE_PROPOSAL_TITLE, proposal.getTitre( ) );
        docFields.put( ParticipatoryBudgetConstants.DOCUMENT_ATTRIBUTE_PROPOSAL_URL,
                "/jsp/site/Portal.jsp?page=proposal&campaign=" + proposal.getCodeCampaign( ) + "&proposal=" + proposal.getCodeProposal( ) );

        docFields.put( ParticipatoryBudgetConstants.DOCUMENT_ATTRIBUTE_STATUS, "SOUMIS" );
        docFields.put( ParticipatoryBudgetConstants.DOCUMENT_ATTRIBUTE_THEME, proposal.getCodeTheme( ) );
        docFields.put( ParticipatoryBudgetConstants.DOCUMENT_ATTRIBUTE_VALUE, "" + proposal.getCout( ) );
        docFields.put( ParticipatoryBudgetConstants.DOCUMENT_ATTRIBUTE_POPULAR_AREA, "" + ( StringUtils.isBlank( proposal.getTypeQpvQva( ) ) ? ParticipatoryBudgetConstants.NOT_POPULAR_AREA_VALUE : proposal.getTypeQpvQva( ) ) );

        // TODO : Create WS Rest API in particip-plugin-participatorybudget, and use it here, rather than directly use ProjectService class... or not.
        return ProjectService.getInstance( ).createproject( title, summary, valid, DOCUMENT_PROJECT_PUBLISH_PORTLET_ID, docFields );
    }

}
