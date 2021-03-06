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

import javax.inject.Inject;

import fr.paris.lutece.plugins.participatoryideation.service.campaign.IdeationCampaignDataProvider;
import fr.paris.lutece.portal.service.util.AppPropertiesService;
import fr.paris.lutece.util.ReferenceItem;
import fr.paris.lutece.util.ReferenceList;

/**
 * This class provides campaign services and informations from plugin-participatorybudget. It overrides some methods to provides data from participatory-budget.
 */
public class ParticipatoryIdeationCampaignModuleDataProvider extends IdeationCampaignDataProvider
{
    @Inject
    private ParticipatoryIdeationCampaignModuleDataFromBudgetService dataFromBudget;

    protected static final String REST_URL = AppPropertiesService.getProperty( "campaign.rest.webapp.url" )
            + AppPropertiesService.getProperty( "campaign.rest.demand.base_url" );

    // *********************************************************************************************
    // * CAMPAIGN CAMPAIGN CAMPAIGN CAMPAIGN CAMPAIGN CAMPAIGN CAMPAIGN CAMPAIGN CAMPAIGN CAMPAIGN *
    // * CAMPAIGN CAMPAIGN CAMPAIGN CAMPAIGN CAMPAIGN CAMPAIGN CAMPAIGN CAMPAIGN CAMPAIGN CAMPAIGN *
    // *********************************************************************************************

    @Override
    public ReferenceList getCampaigns( )
    {
        return dataFromBudget.getCampaigns( );
    }

    // *********************************************************************************************
    // * PHASES PHASES PHASES PHASES PHASES PHASES PHASES PHASES PHASES PHASES PHASES PHASES PHASE *
    // * PHASES PHASES PHASES PHASES PHASES PHASES PHASES PHASES PHASES PHASES PHASES PHASES PHASE *
    // *********************************************************************************************

    @Override
    public boolean isBeforeBeginning( String phase )
    {
        return dataFromBudget.isBeforeBeginning( phase );
    }

    @Override
    public boolean isBeforeBeginning( String codeCampaign, String phase )
    {
        return dataFromBudget.isBeforeBeginning( codeCampaign, phase );
    }

    @Override
    public boolean isAfterBeginning( String phase )
    {
        return dataFromBudget.isAfterBeginning( phase );
    }

    @Override
    public boolean isAfterBeginning( String codeCampaign, String phase )
    {
        return dataFromBudget.isAfterBeginning( codeCampaign, phase );
    }

    @Override
    public boolean isDuring( String phase )
    {
        return dataFromBudget.isDuring( phase );
    }

    @Override
    public boolean isDuring( String codeCampaign, String phase )
    {
        return dataFromBudget.isDuring( codeCampaign, phase );
    }

    @Override
    public boolean isBeforeEnd( String phase )
    {
        return dataFromBudget.isBeforeEnd( phase );
    }

    @Override
    public boolean isBeforeEnd( String codeCampaign, String phase )
    {
        return dataFromBudget.isBeforeEnd( codeCampaign, phase );
    }

    @Override
    public boolean isAfterEnd( String phase )
    {
        return dataFromBudget.isAfterEnd( phase );
    }

    @Override
    public boolean isAfterEnd( String codeCampaign, String phase )
    {
        return dataFromBudget.isAfterEnd( codeCampaign, phase );
    }

    // *********************************************************************************************
    // * AREA AREA AREA AREA AREA AREA AREA AREA AREA AREA AREA AREA AREA AREA AREA AREA AREA AREA *
    // * AREA AREA AREA AREA AREA AREA AREA AREA AREA AREA AREA AREA AREA AREA AREA AREA AREA AREA *
    // *********************************************************************************************

    @Override
    public int getCampaignNumberLocalizedAreas( String codeCampaign )
    {
        return dataFromBudget.getCampaignNumberLocalizedAreas( codeCampaign );
    }

    @Override
    public int getLastCampaignNumberLocalizedAreas( )
    {
        return dataFromBudget.getCampaignNumberLocalizedAreas( );
    }

    @Override
    public ReferenceList getCampaignAllAreaLabels( String codeCampaign )
    {
        return dataFromBudget.getCampaignAllAreas( codeCampaign );
    }

    @Override
    public ReferenceList getLastCampaignAllAreaLabels( )
    {
        return dataFromBudget.getCampaignAllAreas( );
    }

    @Override
    public ReferenceList getCampaignLocalizedAreaLabels( String codeCampaign )
    {
        return dataFromBudget.getCampaignLocalizedAreas( codeCampaign );
    }

    @Override
    public ReferenceList getLastCampaignLocalizedAreaLabels( )
    {
        return dataFromBudget.getCampaignLocalizedAreas( );
    }

    @Override
    public ReferenceItem getCampaignWholeAreaLabel( String codeCampaign )
    {
        return dataFromBudget.getCampaignWholeArea( codeCampaign );
    }

    @Override
    public ReferenceItem getLastCampaignWholeAreaLabel( )
    {
        return dataFromBudget.getCampaignWholeArea( );
    }

    @Override
    public ReferenceList getCampaignAllAreaTypes( String codeCampaign )
    {
    	return null;
    }

    @Override
    public ReferenceList getLastCampaignAllAreaTypes( )
    {
    	return null;
    }

// *********************************************************************************************
    // * THEMES THEMES THEMES THEMES THEMES THEMES THEMES THEMES THEMES THEMES THEMES THEMES THEME *
    // * THEMES THEMES THEMES THEMES THEMES THEMES THEMES THEMES THEMES THEMES THEMES THEMES THEME *
    // *********************************************************************************************

    @Override
    public ReferenceList getCampaignThemes( String codeCampaign )
    {
        return dataFromBudget.getCampaignThemes( codeCampaign );
    }

    @Override
    public ReferenceList getLastCampaignThemes( )
    {
        return dataFromBudget.getCampaignThemes( );
    }

    @Override
    public ReferenceList getCampaignThemesFrontRgb( String codeCampaign )
    {
        return dataFromBudget.getCampaignThemesFrontRgb( codeCampaign );
    }

}
