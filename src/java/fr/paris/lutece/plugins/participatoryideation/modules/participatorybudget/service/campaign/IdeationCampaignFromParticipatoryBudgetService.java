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
package fr.paris.lutece.plugins.participatoryideation.modules.participatorybudget.service.campaign;

import java.util.Collection;

import fr.paris.lutece.plugins.participatorybudget.business.campaign.Campagne;
import fr.paris.lutece.plugins.participatorybudget.business.campaign.CampagneHome;
import fr.paris.lutece.plugins.participatoryideation.service.campaign.IIdeationCampaignService;
import fr.paris.lutece.plugins.participatoryideation.service.rest.AbstractRestBasedService;
import fr.paris.lutece.portal.service.util.AppPropertiesService;
import fr.paris.lutece.util.ReferenceList;

/**
 * This class provides campaign services and informations from plugin-participatorybudget. It uses the REST API of the plugin.
 */
public class IdeationCampaignFromParticipatoryBudgetService extends AbstractRestBasedService implements IIdeationCampaignService
{

    private static final String REST_URL = AppPropertiesService.getProperty( "participatoryideation.campaign.rest.webapp.url" )
            + AppPropertiesService.getProperty( "participatoryideation.campaign.rest.demand.base_url" );

    // *********************************************************************************************
    // * CAMPAIGN CAMPAIGN CAMPAIGN CAMPAIGN CAMPAIGN CAMPAIGN CAMPAIGN CAMPAIGN CAMPAIGN CAMPAIGN *
    // * CAMPAIGN CAMPAIGN CAMPAIGN CAMPAIGN CAMPAIGN CAMPAIGN CAMPAIGN CAMPAIGN CAMPAIGN CAMPAIGN *
    // *********************************************************************************************

    // Provides list of campaigns
    public ReferenceList getCampaigns( )
    {
        ReferenceList allAreas = new ReferenceList( );

        Collection<Campagne> listCampaign = CampagneHome.getCampagnesList( );
        for ( Campagne campaign : listCampaign )
        {
            allAreas.addItem( campaign.getCode( ), campaign.getTitle( ) );
        }

        return allAreas;
    }

    // *********************************************************************************************
    // * PHASES PHASES PHASES PHASES PHASES PHASES PHASES PHASES PHASES PHASES PHASES PHASES PHASE *
    // * PHASES PHASES PHASES PHASES PHASES PHASES PHASES PHASES PHASES PHASES PHASES PHASES PHASE *
    // *********************************************************************************************

    @Override
    public boolean isBeforeBeginning( String phase )
    {
        return parseBoolean( REST_URL + phase + "/before-beginning" );
    }

    @Override
    public boolean isBeforeBeginning( String codeCampaign, String phase )
    {
        return parseBoolean( REST_URL + codeCampaign + "/" + phase + "/before-beginning" );
    }

    @Override
    public boolean isAfterBeginning( String phase )
    {
        return parseBoolean( REST_URL + phase + "/after-beginning" );
    }

    @Override
    public boolean isAfterBeginning( String codeCampaign, String phase )
    {
        return parseBoolean( REST_URL + codeCampaign + "/" + phase + "/after-beginning" );
    }

    @Override
    public boolean isDuring( String phase )
    {
        return parseBoolean( REST_URL + phase + "/during" );
    }

    @Override
    public boolean isDuring( String codeCampaign, String phase )
    {
        return parseBoolean( REST_URL + codeCampaign + "/" + phase + "/during" );
    }

    @Override
    public boolean isBeforeEnd( String phase )
    {
        return parseBoolean( REST_URL + phase + "/before-end" );
    }

    @Override
    public boolean isBeforeEnd( String codeCampaign, String phase )
    {
        return parseBoolean( REST_URL + codeCampaign + "/" + phase + "/before-end" );
    }

    @Override
    public boolean isAfterEnd( String phase )
    {
        return parseBoolean( REST_URL + phase + "/after-end" );
    }

    @Override
    public boolean isAfterEnd( String codeCampaign, String phase )
    {
        return parseBoolean( REST_URL + codeCampaign + "/" + phase + "/after-end" );
    }

    // *********************************************************************************************
    // * AREA AREA AREA AREA AREA AREA AREA AREA AREA AREA AREA AREA AREA AREA AREA AREA AREA AREA *
    // * AREA AREA AREA AREA AREA AREA AREA AREA AREA AREA AREA AREA AREA AREA AREA AREA AREA AREA *
    // *********************************************************************************************

    @Override
    public int getCampaignNumberLocalizedAreas( String codeCampaign )
    {
        return countValueList( REST_URL + codeCampaign + "/localized-areas" );
    }

    @Override
    public int getCampaignNumberLocalizedAreas( )
    {
        return countValueList( REST_URL + "localized-areas" );
    }

    @Override
    public ReferenceList getCampaignAllAreas( String codeCampaign )
    {
        return parseValueList( REST_URL + codeCampaign + "/all-areas" );
    }

    @Override
    public ReferenceList getCampaignAllAreas( )
    {
        return parseValueList( REST_URL + "all-areas" );
    }

    @Override
    public ReferenceList getCampaignLocalizedAreas( String codeCampaign )
    {
        return parseValueList( REST_URL + codeCampaign + "/localized-areas" );
    }

    @Override
    public ReferenceList getCampaignLocalizedAreas( )
    {
        return parseValueList( REST_URL + "localized-areas" );
    }

    @Override
    public String getCampaignWholeArea( String codeCampaign )
    {
        return parseString( REST_URL + codeCampaign + "/whole-area" );
    }

    @Override
    public String getCampaignWholeArea( )
    {
        return parseString( REST_URL + "whole-area" );
    }

    // *********************************************************************************************
    // * THEMES THEMES THEMES THEMES THEMES THEMES THEMES THEMES THEMES THEMES THEMES THEMES THEME *
    // * THEMES THEMES THEMES THEMES THEMES THEMES THEMES THEMES THEMES THEMES THEMES THEMES THEME *
    // *********************************************************************************************

    @Override
    public ReferenceList getCampaignThemes( String codeCampaign )
    {
        return parseReferenceList( REST_URL + codeCampaign + "/themes" );
    }

    @Override
    public ReferenceList getCampaignThemes( )
    {
        return parseReferenceList( REST_URL + "themes" );
    }

}
