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
package fr.paris.lutece.plugins.participatoryideation.modules.participatorybudget.service.ideation;

import org.json.JSONObject;

import fr.paris.lutece.plugins.participatoryideation.service.campaign.IIdeationCampaignService;
import fr.paris.lutece.plugins.participatoryideation.service.rest.AbstractRestBasedService;
import fr.paris.lutece.portal.service.util.AppPropertiesService;
import fr.paris.lutece.util.ReferenceList;

/**
 * This class provides campaign services and informations from plugin-participatorybudget. It uses the REST API of the plugin.
 */
public class ParticipatoryIdeationCampaignModuleService extends AbstractRestBasedService implements IIdeationCampaignService
{

    protected static final String REST_URL = AppPropertiesService.getProperty( "campaign.rest.webapp.url" )
            + AppPropertiesService.getProperty( "campaign.rest.demand.base_url" );

    // *********************************************************************************************
    // * CAMPAIGN CAMPAIGN CAMPAIGN CAMPAIGN CAMPAIGN CAMPAIGN CAMPAIGN CAMPAIGN CAMPAIGN CAMPAIGN *
    // * CAMPAIGN CAMPAIGN CAMPAIGN CAMPAIGN CAMPAIGN CAMPAIGN CAMPAIGN CAMPAIGN CAMPAIGN CAMPAIGN *
    // *********************************************************************************************

    // Provides list of campaigns
    @Override
    public ReferenceList getCampaigns( )
    {
        JSONObject json = doGetJSon( REST_URL + "campaigns" );
        return parseReferenceList( json );
    }

    // *********************************************************************************************
    // * PHASES PHASES PHASES PHASES PHASES PHASES PHASES PHASES PHASES PHASES PHASES PHASES PHASE *
    // * PHASES PHASES PHASES PHASES PHASES PHASES PHASES PHASES PHASES PHASES PHASES PHASES PHASE *
    // *********************************************************************************************

    @Override
    public boolean isBeforeBeginning( String phase )
    {
        JSONObject json = doGetJSon( REST_URL + phase + "/before-beginning" );
        return parseBoolean( json );
    }

    @Override
    public boolean isBeforeBeginning( String codeCampaign, String phase )
    {
        JSONObject json = doGetJSon( REST_URL + codeCampaign + "/" + phase + "/before-beginning" );
        return parseBoolean( json );
    }

    @Override
    public boolean isAfterBeginning( String phase )
    {
        JSONObject json = doGetJSon( REST_URL + phase + "/after-beginning" );
        return parseBoolean( json );
    }

    @Override
    public boolean isAfterBeginning( String codeCampaign, String phase )
    {
        JSONObject json = doGetJSon( REST_URL + codeCampaign + "/" + phase + "/after-beginning" );
        return parseBoolean( json );
    }

    @Override
    public boolean isDuring( String phase )
    {
        JSONObject json = doGetJSon( REST_URL + phase + "/during" );
        return parseBoolean( json );
    }

    @Override
    public boolean isDuring( String codeCampaign, String phase )
    {
        JSONObject json = doGetJSon( REST_URL + codeCampaign + "/" + phase + "/during" );
        return parseBoolean( json );
    }

    @Override
    public boolean isBeforeEnd( String phase )
    {
        JSONObject json = doGetJSon( REST_URL + phase + "/before-end" );
        return parseBoolean( json );
    }

    @Override
    public boolean isBeforeEnd( String codeCampaign, String phase )
    {
        JSONObject json = doGetJSon( REST_URL + codeCampaign + "/" + phase + "/before-end" );
        return parseBoolean( json );
    }

    @Override
    public boolean isAfterEnd( String phase )
    {
        JSONObject json = doGetJSon( REST_URL + phase + "/after-end" );
        return parseBoolean( json );
    }

    @Override
    public boolean isAfterEnd( String codeCampaign, String phase )
    {
        JSONObject json = doGetJSon( REST_URL + codeCampaign + "/" + phase + "/after-end" );
        return parseBoolean( json );
    }

    // *********************************************************************************************
    // * AREA AREA AREA AREA AREA AREA AREA AREA AREA AREA AREA AREA AREA AREA AREA AREA AREA AREA *
    // * AREA AREA AREA AREA AREA AREA AREA AREA AREA AREA AREA AREA AREA AREA AREA AREA AREA AREA *
    // *********************************************************************************************

    @Override
    public int getCampaignNumberLocalizedAreas( String codeCampaign )
    {
        JSONObject json = doGetJSon( REST_URL + codeCampaign + "/localized-areas" );
        return countValueList( json );
    }

    @Override
    public int getCampaignNumberLocalizedAreas( )
    {
        JSONObject json = doGetJSon( REST_URL + "localized-areas" );
        return countValueList( json );
    }

    @Override
    public ReferenceList getCampaignAllAreas( String codeCampaign )
    {
        JSONObject json = doGetJSon( REST_URL + codeCampaign + "/all-areas" );
        return parseValueList( json );
    }

    @Override
    public ReferenceList getCampaignAllAreas( )
    {
        JSONObject json = doGetJSon( REST_URL + "all-areas" );
        return parseValueList( json );
    }

    @Override
    public ReferenceList getCampaignLocalizedAreas( String codeCampaign )
    {
        JSONObject json = doGetJSon( REST_URL + codeCampaign + "/localized-areas" );
        return parseValueList( json );
    }

    @Override
    public ReferenceList getCampaignLocalizedAreas( )
    {
        JSONObject json = doGetJSon( REST_URL + "localized-areas" );
        return parseValueList( json );
    }

    @Override
    public String getCampaignWholeArea( String codeCampaign )
    {
        JSONObject json = doGetJSon( REST_URL + codeCampaign + "/whole-area" );
        return parseString( json );
    }

    @Override
    public String getCampaignWholeArea( )
    {
        JSONObject json = doGetJSon( REST_URL + "whole-area" );
        return parseString( json );
    }

    // *********************************************************************************************
    // * THEMES THEMES THEMES THEMES THEMES THEMES THEMES THEMES THEMES THEMES THEMES THEMES THEME *
    // * THEMES THEMES THEMES THEMES THEMES THEMES THEMES THEMES THEMES THEMES THEMES THEMES THEME *
    // *********************************************************************************************

    @Override
    public ReferenceList getCampaignThemes( String codeCampaign )
    {
        JSONObject json = doGetJSon( REST_URL + codeCampaign + "/themes" );
        return parseReferenceList( json );
    }

    @Override
    public ReferenceList getCampaignThemes( )
    {
        JSONObject json = doGetJSon( REST_URL + "themes" );
        return parseReferenceList( json );
    }

    @Override
    public ReferenceList getCampaignThemesFrontRgb( String codeCampaign )
    {
        JSONObject json = doGetJSon( REST_URL + codeCampaign + "/themes-front-rgb" );
        return parseReferenceList( json );
    }

}
