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

import java.util.Collection;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import fr.paris.lutece.plugins.participatorybudget.service.campaign.event.CampaignEvent;
import fr.paris.lutece.plugins.participatorybudget.service.campaign.event.CampaignEventListener;
import fr.paris.lutece.plugins.participatoryideation.business.depositary.CampagneDepositaire;
import fr.paris.lutece.plugins.participatoryideation.business.depositary.CampagneDepositaireHome;
import fr.paris.lutece.plugins.participatoryideation.business.depositary.DepositaireTypeHome;
import fr.paris.lutece.plugins.participatoryideation.business.proposal.ProposalHome;
import fr.paris.lutece.plugins.participatoryideation.business.proposal.ProposalSearcher;

public class ParticipatoryIdeationCampagneEventListener implements CampaignEventListener
{

    @Override
    public String processCampaignEvent( CampaignEvent campaignEvent )
    {
        switch( campaignEvent.getEventType( ) )
        {
            case CampaignEvent.CAMPAIGN_CLONED:
                return process_CAMPAIGN_CLONED( campaignEvent );

            case CampaignEvent.CAMPAIGN_CODE_MODIFICATION_AUTHORISATION:
                return process_CAMPAIGN_CODE_MODIFICATION_AUTHORISATION( campaignEvent );

            case CampaignEvent.CAMPAIGN_CODE_MODIFIED:
                return process_CAMPAIGN_CODE_MODIFIED( campaignEvent );
        }

        return ""; // The event is not handled.
    }

    // *********************************************************************************************
    // * CLONE CLONE CLONE CLONE CLONE CLONE CLONE CLONE CLONE CLONE CLONE CLONE CLONE CLONE CLONE *
    // * CLONE CLONE CLONE CLONE CLONE CLONE CLONE CLONE CLONE CLONE CLONE CLONE CLONE CLONE CLONE *
    // *********************************************************************************************

    private String process_CAMPAIGN_CLONED( CampaignEvent campaignEvent )
    {
        String clonedCampaignCode = campaignEvent.getLinkedCampaign( ).getCode( );
        String newCampaignCode = campaignEvent.getMainCampaign( ).getCode( );

        // Clone depositaries
        Collection<CampagneDepositaire> depositaries = CampagneDepositaireHome.getCampagneDepositaireListByCampagne( clonedCampaignCode );
        for ( CampagneDepositaire depositary : depositaries )
        {
            CampagneDepositaire newDepositary = new CampagneDepositaire( );
            newDepositary.setCodeDepositaireType( depositary.getCodeDepositaireType( ) );
            newDepositary.setCodeCampagne( newCampaignCode );

            CampagneDepositaireHome.create( newDepositary );
        }

        return StringUtils.EMPTY;
    }

    // *********************************************************************************************
    // * CODE CODE CODE CODE CODE CODE CODE CODE CODE CODE CODE CODE CODE CODE CODE CODE CODE CODE *
    // * CODE CODE CODE CODE CODE CODE CODE CODE CODE CODE CODE CODE CODE CODE CODE CODE CODE CODE *
    // *********************************************************************************************

    private String process_CAMPAIGN_CODE_MODIFICATION_AUTHORISATION( CampaignEvent campaignEvent )
    {
        ProposalSearcher searcher = new ProposalSearcher( );
        searcher.setCodeCampagne( campaignEvent.getMainCampaign( ).getCode( ) );

        if ( CollectionUtils.isNotEmpty( ProposalHome.getProposalsListSearch( searcher ) ) )
        {
            return "Existing proposal(s) with this code.";
        }
        else
        {
            return StringUtils.EMPTY;
        }
    }

    private String process_CAMPAIGN_CODE_MODIFIED( CampaignEvent campaignEvent )
    {
        // Change code in depositary data
        CampagneDepositaireHome.changeCampainCode( campaignEvent.getMainCampaign( ).getCode( ), campaignEvent.getLinkedCampaign( ).getCode( ) );

        return StringUtils.EMPTY;
    }

}
